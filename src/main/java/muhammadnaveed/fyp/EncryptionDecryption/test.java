//package muhammadnaveed.fyp.EncryptionDecryption;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.UUID;
//
//public class test {
//    private static ObjectMapper mapper = new ObjectMapper();
//
//    public static void main(String[] args) {
//        String theDocument = "{ \\\"output\\\" : \\\"hello  world\\\"}";
//        String thePassword = "passw0rd";
//
//        try {
//            System.out.println("Initial: " + theDocument);
//            String output = encrypt2layer(theDocument, thePassword);
//            System.out.println("Output: " + output);
//            String reverse = decrypt2layer(output, thePassword);
//            System.out.println("Result: " + reverse);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
////    public static String encrypt2layer(String content, String password) throws Exception {
////        Encryption enc = new Encryption();
////        String pwd = UUID.randomUUID().toString();
////        // Encrypt the Document using the UUID
////        String encDoc = enc.encrypt(content, pwd);
////
////        // Create an Encrypted Document (containing the output + the UUID)
////        // and convert it to JSON
////        String json = mapper.writeValueAsString(new EncryptedDocument(pwd, encDoc));
////
////        // Encrypt the JSON using the password
////        return enc.encrypt(json, password);
////    }
//
////    public static String decrypt2layer(String content, String password) throws Exception {
//////        Decryption dec = new Decryption();
//////
//////        // Decrypt the document using the password
//////        String json = dec.decrypt(content, password);
//////
//////        // Convert the JSON into an EncryptedDocument object
//////        EncryptedDocument ed = mapper.readValue(json, EncryptedDocument.class);
//////
//////        // Decrypt and return the encrypted document
//////        return dec.decrypt(ed.getContent(), ed.getUuid());
//////    }
//}
