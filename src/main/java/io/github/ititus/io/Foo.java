package io.github.ititus.io;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipInputStream;

public class Foo {

    private static final boolean ENABLE_PROXY = false;
    private static final Path DIR = PathUtil.resolveRealDir(Path.of(System.getProperty("user.home"), "Desktop/quizduel"));
    private static final HttpClient HTTP_CLIENT;
    private static final String KEY = "398C7E2774E7A196AF30DFED78762328427E1F1EAD4C1F5D0D86CE44948E1CB0";
    private static final String ZERO_SALT = "0".repeat(32);

    private static final String LOGIN_BY_DEVICE_SALT = "B7DA6D65AC432548AC174ECF4159AE07";
    private static final String BODY_LOGIN_BY_DEVICE = /*\uFEFF*/ "{\"deviceId\":\"1C92FD8C-9E81-4918-B235-7C7AD08E346E\",\"advertisingId\":\"\",\"quizDuelLocale\":\"de_DE\",\"userId\":11104117387,\"applicationId\":0,\"applicationKey\":\"7mj63q1afcd5\",\"timestamp\":\"1630576066406\",\"localTimestamp\":\"1630583266406\",\"localTimeZone\":\"Europe/Berlin\",\"version\":\"1.16.2\",\"session\":null,\"ticket\":\"e5e8ec5589a5b341839098e0c88665875e380fe2\",\"platform\":\"ios\",\"locale\":\"en_DE\",\"premium\":false,\"deviceModel\":\"iPhone10,6\",\"osVersion\":\"iOS 14.7.1\",\"clientGeneratedId\":\"F0AD05BC-A85C-4C4A-AA82-56BB782D9615\",\"applicationActivityStatus\":{\"activityId\":0,\"presenceBucketUpdates\":null},\"tracks\":null}";

    static {
        SSLContext ctx;
        try {
            ctx = SSLContext.getInstance("SSL");
            ctx.init(new KeyManager[0], new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            }, new SecureRandom());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        HttpClient.Builder b = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL);
        if (ENABLE_PROXY) {
            b = b
                    .sslContext(ctx)
                    .proxy(ProxySelector.of(new InetSocketAddress("localhost", 8888)));
        }

        HTTP_CLIENT = b.build();
    }

    public static void main(String[] args) {
        decryptBodies();
        // apiRequests();
    }

    private static void decryptBodies() {
        decryptBody("api_user_quizduel_loginByDevice_request.dat", "B7DA6D65AC432548AC174ECF4159AE07");
        decryptBody("api_user_quizduel_loginByDevice_response.dat", "B7DA6D65AC432548AC174ECF4159AE07");
        decryptBody("api_multiplayer_refresh_request.dat", "A04E3DC0ABA6769228BBEBC2BC105437");
        decryptBody("api_multiplayer_refresh_response.dat", "A04E3DC0ABA6769228BBEBC2BC105437");
        decryptBody("api_userstate_readUserState2_1_request.dat", "5846D04F46D0050CAE0DE5029CDE710D");
        decryptBody("api_userstate_readUserState2_1_response.dat", "5846D04F46D0050CAE0DE5029CDE710D");
        decryptBody("api_happenings_list_request.dat", "6ED4A4FC96D14B6B776609F7BCFB7E73");
        decryptBody("api_happenings_list_response.dat", "6ED4A4FC96D14B6B776609F7BCFB7E73");
        decryptBody("api_user_quizduel_getQuizzes_request.dat", "F77E49EE95EBC08551FDD2E1419C1D10");
        decryptBody("api_user_quizduel_getQuizzes_response.dat", "F77E49EE95EBC08551FDD2E1419C1D10");
        decryptBody("api_user_registerPushId_request.dat", "4A713540CDD9D9B9AE86C1105D92BAE6");
        decryptBody("api_user_registerPushId_response.dat", "4A713540CDD9D9B9AE86C1105D92BAE6");
        decryptBody("api_user_quizduel_getChatMessages_request.dat", "D15FD85C374D290CA44F9BF811CC50B3");
        decryptBody("api_user_quizduel_getChatMessages_response.dat", "D15FD85C374D290CA44F9BF811CC50B3");
        decryptBody("api_user_quizduel_sendChatMessages_request.dat", "C41065B3E07289A58F235E8EEF9D45E6");
        decryptBody("api_user_quizduel_sendChatMessages_response.dat", "C41065B3E07289A58F235E8EEF9D45E6");
        decryptBody("api_userstate_writeUserState2_1_request.dat", "9908633289FF5CE347A62D12679DD755");
        decryptBody("api_userstate_writeUserState2_1_response.dat", "9908633289FF5CE347A62D12679DD755");
        decryptBody("api_game_common_settings_request.dat", "AA6EDF96217A6B83D40F6F701EAABD77");
        decryptBody("api_game_common_settings_response.dat", "AA6EDF96217A6B83D40F6F701EAABD77");
        decryptBody("api_game_common_registerCustomEventBatch_request.dat", "F49C84012B4B41270DB629732FC77584");
        decryptBody("api_game_common_registerCustomEventBatch_response.dat", "F49C84012B4B41270DB629732FC77584");
        decryptBody("api_multiplayer_invokeEngineCommands_request.dat", "41D840350FA57259FBF390B2AD9C7E1C");
        decryptBody("api_multiplayer_invokeEngineCommands_response.dat", "41D840350FA57259FBF390B2AD9C7E1C");
    }

    private static void apiRequests() {
        doApiRequest("/user/quizduel/loginByDevice", ZERO_SALT, BODY_LOGIN_BY_DEVICE);
        // doApiRequest("/multiplayer/refresh", ZERO_SALT, );
    }

    private static void decryptBody(String fileName, String salt) {
        Path in = PathUtil.resolveRealFile(DIR.resolve(fileName));
        if (PathUtil.getExtension(in).map("json"::equals).orElse(false)) {
            throw new IllegalArgumentException();
        }

        Path out = in.resolveSibling(PathUtil.getNameWithoutExtension(in) + ".json");

        byte[] data;
        try {
            data = Files.readAllBytes(in);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        String json = bytesToString(decryptDecompress(salt, data));
        System.out.println(json);
        try {
            Files.writeString(out, json);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static String doApiRequest(String route, String salt, String body) {
        URI uri = URI.create("https://davincigameserver.appspot.com/api" + route);
        HttpRequest req = HttpRequest.newBuilder()
                .uri(uri)
                .POST(HttpRequest.BodyPublishers.ofByteArray(compressEncrypt(salt, body.getBytes(StandardCharsets.UTF_8)).toByteArray()))
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "en-us")
                .header("User-Agent", "Ios/New QuizDuel/1.16.2")
                .header("Content-Type", "application/json")
                .header("payload-session", salt)
                .header("X-Davinci-GZIP-Compression", "True")
                .header("X-Unity-Version", "2019.4.15f1")
                .build();
        System.out.println("Sending:");
        System.out.println(req);
        System.out.println(body);

        HttpResponse<InputStream> resp;
        try {
            resp = HTTP_CLIENT.send(req, HttpResponse.BodyHandlers.ofInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Receiving:");
        System.out.println(resp);
        System.out.println("Status: " + HttpStatus.of(resp.statusCode()));
        System.out.println("Headers:");
        resp.headers().map().forEach((k, v) -> System.out.println(k + ": " + (v.size() == 1 ? v.get(0) : v)));
        System.out.println("Body:");
        String responseJson = bytesToString(decryptDecompress(salt, extractBody(resp)));
        System.out.println(responseJson);
        return responseJson;
    }

    private static byte[] decryptDecompress(String salt, HttpResponse<InputStream> response) {
        return decryptDecompress(salt, response.body());
    }

    private static byte[] decryptDecompress(String salt, byte[] data) {
        return decryptDecompress(salt, data, false);
    }

    private static byte[] decryptDecompress(String salt, byte[] data, boolean bypassCompression) {
        return decryptDecompress(salt, new ByteArrayInputStream(data), bypassCompression);
    }

    private static byte[] decryptDecompress(String salt, InputStream is) {
        return decryptDecompress(salt, is, false);
    }

    private static byte[] decryptDecompress(String salt, InputStream is, boolean bypassCompression) {
        byte[] keyBytes = hexStringToBytes(KEY);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

        byte[] ivBytes = hexStringToBytes(salt);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        Cipher c;
        try {
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, key, iv);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (bypassCompression) {
            try (
                    is;
                    CipherInputStream cis = new CipherInputStream(is, c)
            ) {
                return cis.readAllBytes();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try (
                    is;
                    CipherInputStream cis = new CipherInputStream(is, c);
                    GZIPInputStream zis = new GZIPInputStream(cis)
            ) {
                return zis.readAllBytes();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static ByteArrayOutputStream compressEncrypt(String salt, byte[] data) {
        return compressEncrypt(salt, data, false);
    }

    private static ByteArrayOutputStream compressEncrypt(String salt, byte[] data, boolean bypassCompression) {
        byte[] keyBytes = hexStringToBytes(KEY);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

        byte[] ivBytes = hexStringToBytes(salt);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        Cipher c;
        try {
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, key, iv);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        if (bypassCompression) {
            try (
                    CipherOutputStream cos = new CipherOutputStream(bos, c)
            ) {
                cos.write(data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try (
                    CipherOutputStream cos = new CipherOutputStream(bos, c);
                    GZIPOutputStream zos = new GZIPOutputStream(cos)
            ) {
                zos.write(data);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return bos;
    }

    private static byte[] hexStringToBytes(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex, 2 * i, 2 * i + 2, 16);
        }

        return bytes;
    }

    private static String bytesToString(byte[] bytes) {
        CharsetDecoder cd = StandardCharsets.UTF_8.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
        ByteBuffer bb = ByteBuffer.wrap(Arrays.copyOf(bytes, bytes.length));
        try {
            return cd.decode(bb).toString();
        } catch (CharacterCodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream extractBody(HttpResponse<InputStream> response) {
        List<String> encodings = response.headers().allValues("Content-Encoding");
        if (encodings.size() > 1) {
            throw new RuntimeException("Multiple encoding methods not supported");
        }

        if (encodings.isEmpty()) {
            return response.body();
        }

        String encoding = encodings.get(0);
        if ("gzip".equals(encoding) || "x-gzip".equals(encoding)) {
            try {
                return new GZIPInputStream(response.body());
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        } else if ("deflate".equals(encoding)) {
            return new ZipInputStream(response.body());
        }

        throw new RuntimeException("Encoding " + encoding + " is not supported");
    }
}
