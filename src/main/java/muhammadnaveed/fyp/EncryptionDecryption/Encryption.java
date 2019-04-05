package muhammadnaveed.fyp.EncryptionDecryption;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.util.UUID;

public class Encryption {

    private static ObjectMapper mapper = new ObjectMapper();


    public String encrypt(String record, String patientSecret) throws Exception {
        byte[] ivBytes;
        /*you can give whatever you want for patientSecret. This is for testing purpose*/
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        // Derive the key
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(patientSecret.toCharArray(), bytes, 65556, 256);
        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
        //encrypting the record
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        AlgorithmParameters params = cipher.getParameters();
        ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
        byte[] encryptedTextBytes = cipher.doFinal(record.getBytes(StandardCharsets.UTF_8));
        //prepend salt and vi
        byte[] buffer = new byte[bytes.length + ivBytes.length + encryptedTextBytes.length];
        System.arraycopy(bytes, 0, buffer, 0, bytes.length);
        System.arraycopy(ivBytes, 0, buffer, bytes.length, ivBytes.length);
        System.arraycopy(encryptedTextBytes, 0, buffer, bytes.length + ivBytes.length, encryptedTextBytes.length);
        return new Base64().encodeToString(buffer);
    }

    public String encrypt2layer(String content, String password) throws Exception {
        Encryption enc = new Encryption();
        String pwd = UUID.randomUUID().toString();

        // Encrypt the Document using the UUID
        String encDoc = enc.encrypt(content, pwd);

        // Create an Encrypted Document (containing the output + the UUID)
        // and convert it to JSON
        String json = mapper.writeValueAsString(new EncryptedDocument(pwd, encDoc));

        // Encrypt the JSON using the password & return
        return enc.encrypt(json, password);
    }
}