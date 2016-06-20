package org.swisspush.vertx.examples;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.File;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

public class CryptoVerticle extends AbstractVerticle {

    private Logger log = LoggerFactory.getLogger(CryptoVerticle.class);

    private Cipher ecipher;
    private Cipher dcipher;

    private byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35,
            (byte) 0xE3, (byte) 0x03 };

    private String passPhrase = "ImSecret";

    private LineIterator it;

    private long lap = -1;

    @Override
    public void start() {

        int iterationCount = 2;
        KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);

        try {
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        } catch(Exception e) {
            throw new IllegalStateException(e);
        }


        vertx.createHttpServer().requestHandler(request -> {
            request.response().end("I'm the common verticle!");
        }).listen(8080);

        initializeLineIterator();

        vertx.eventBus().consumer("encrypt", new Handler<Message<Object>>() {
            @Override
            public void handle(Message<Object> event) {
                String encrypted = encrypt(event.body().toString());
                //log.info("encrypted: " + encrypted);
                vertx.eventBus().send("decrypt", encrypted);
            }
        });

        vertx.eventBus().consumer("decrypt", new Handler<Message<Object>>() {
            @Override
            public void handle(Message<Object> event) {
                String decrypted = decrypt(event.body().toString());
                //log.info("decrypted: " + decrypted);

                String nextLine;
                if(it.hasNext()) {
                    nextLine = it.nextLine();
                } else {
                    initializeLineIterator();
                    nextLine = it.nextLine();
                }
                //log.info("send next line to encrypt: " + nextLine);
                vertx.eventBus().send("encrypt", nextLine);
            }
        });

        vertx.eventBus().send("encrypt", it.nextLine());

    }

    private String encrypt(String str) {
        try {
            return new BASE64Encoder().encode(ecipher.doFinal(str.getBytes()));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private String decrypt(String str) {
        try {
            return new String(dcipher.doFinal(new BASE64Decoder().decodeBuffer(str)));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private void initializeLineIterator() {
        try {

            long now = System.currentTimeMillis();
            if(lap != -1){
                log.info(now - lap + " millis used to encrypt/decrypt the old testament");
            }
            lap = now;

            File file = new File(getClass().getClassLoader().getResource("kjv10.txt").getFile());
            it = FileUtils.lineIterator(file, "UTF-8");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
