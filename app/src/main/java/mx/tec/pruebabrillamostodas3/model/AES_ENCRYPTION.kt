package mx.tec.pruebabrillamostodas3.model

import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

fun generateAESKey(keySize: Int = 256): SecretKey {
    val keyGenerator = KeyGenerator.getInstance("AES")
    keyGenerator.init(keySize)
    return keyGenerator.generateKey()
}
fun aesEncrypt(data: ByteArray, secretKey: SecretKey): ByteArray {
    val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    val ivParameterSpec = IvParameterSpec(ByteArray(16)) // Use a secure IV in production
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)
    return cipher.doFinal(data)
}