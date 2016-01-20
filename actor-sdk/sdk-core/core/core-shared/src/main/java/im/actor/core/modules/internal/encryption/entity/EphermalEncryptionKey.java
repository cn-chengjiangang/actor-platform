package im.actor.core.modules.internal.encryption.entity;

import java.io.IOException;

import im.actor.runtime.bser.BserObject;
import im.actor.runtime.bser.BserValues;
import im.actor.runtime.bser.BserWriter;

public class EphermalEncryptionKey extends BserObject {

    private EncryptionKey encryptionKey;
    private boolean isUploaded;

    public EphermalEncryptionKey(EncryptionKey encryptionKey, boolean isUploaded) {
        this.encryptionKey = encryptionKey;
        this.isUploaded = isUploaded;
    }

    public EphermalEncryptionKey(byte[] data) throws IOException {
        load(data);
    }

    public EncryptionKey getEncryptionKey() {
        return encryptionKey;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public EphermalEncryptionKey markUploaded() {
        return new EphermalEncryptionKey(encryptionKey, true);
    }

    @Override
    public void parse(BserValues values) throws IOException {
        encryptionKey = new EncryptionKey(values.getBytes(1));
        isUploaded = values.getBool(2);
    }

    @Override
    public void serialize(BserWriter writer) throws IOException {
        writer.writeBytes(1, encryptionKey.toByteArray());
        writer.writeBool(2, isUploaded);
    }
}