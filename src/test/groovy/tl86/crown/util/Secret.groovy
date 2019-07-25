package tl86.crown.util

import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.PBEParameterSpec
import java.security.spec.AlgorithmParameterSpec
import java.security.spec.KeySpec

class Secret {
    private static byte[] salt = [ (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3, (byte) 0x03]

    static String encrypt(String secretKey, String plainText) throws Exception
    {    KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, 19)
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec)
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, 19)
        Cipher cipher = Cipher.getInstance(key.getAlgorithm())
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec)
        String charSet = "UTF-8"
        byte[] input = plainText.getBytes(charSet)
        byte[] output = cipher.doFinal(input)
        return new String(Base64.getEncoder().encode(output))}

    static String decrypt(String secretKey, String encryptedText) throws Exception
    {    KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt, 19)
        SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec)
        AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, 19)
        Cipher cipher = Cipher.getInstance(key.getAlgorithm())
        cipher.init(Cipher.DECRYPT_MODE, key, paramSpec)
        byte[] enc = Base64.getDecoder().decode(encryptedText)
        byte[] utf8 = cipher.doFinal(enc)
        String charSet = "UTF-8"
        return new String(utf8, charSet)}


    static void main(String[] args) throws Exception
    {   String key = "apiTestStudy"
        String plain = "root"
        String enc = encrypt(key, plain)
        println("Original text: " + plain)
        println("Encrypted text: " + enc)
        String encs="/DgMfJk8Y7YvMrMBiD881A=="
        String plainAfter = decrypt(key, encs)
        println("Original text after decryption: " + plainAfter)
    }
}

