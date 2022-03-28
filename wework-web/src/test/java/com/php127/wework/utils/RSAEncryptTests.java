package com.php127.wework.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class RSAEncryptTests {

    protected String privateKey =
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCjeb5MFjnI+Xoe" +
                    "z5whw0cZG0jzNaVKxpAWJPPkqL2zfOvS0SmHIIMT988Hzh04Bdxt8WUMWv0W/Bvz" +
                    "H39ZTpTzrGgtx+DTnqZN0IRDKozTDEBD2lSfrMpUy6dTrwsDCTR+vISaYG8cad2Q" +
                    "ooeTmRRqokqYTwe/YsM/xhpsDtmVHYRaehzPK23/T8q9iBIXgU31kVSwhrRKegzm" +
                    "xDX9Ve8jRCEL+xN/dPmKBvFA6Wz5t7jAJZ+dmdBqaRuRpffkweZ1zy+CbvE0j0mL" +
                    "5ddEco/+p/uL4I1qLhxQvZuV3x1dA5ou7DmEqeVq4o6HUeFZwlvE4X+pzc9PotDF" +
                    "qi4A2fnpAgMBAAECggEBAIMgLBwr1731HpeIP9x1Hpps4F71MNiB3SFmEpvfHgrS" +
                    "sPYw//Z7haiGpXHFSnqdeOpXLo5yjX/aSTECmsuv1JqAODBAm34jS5IvJ2gYwUyo" +
                    "DwReJHLPzpuln1nolhShVZIy9Mo/f+Byql5RgB5MM6w08VCqU7SYK7UD2j3Cy5Rn" +
                    "CDdf2N3mluNtcZie8/SJR6A1QkhlGfmTWrK4wXugf95qzppIwn9eP52n42E1mYa7" +
                    "bk2yBoNqTRhOv2AqWyC7daOJ5tvbaa9N2ATabepncL98ISS0kGcNKzBEHA6sqYEv" +
                    "o3KMp7C2oBA5jl7BkQrYKuOX6dFUJlK2AqVb9fIE2WECgYEA1fIzdKmAJnFWPfeg" +
                    "YpNsFM8YLY/NFYbbwaGqAPVrdqvyM2aNWQrPfO2tzfSZObZ53Ev/xc6sFFOutnCs" +
                    "b3S/TpSzFYhqjXDByKqJKyxDnCLYrr22HvSg7j6mC7aW+MDWpDus9kKbeIaOl+Mn" +
                    "X7f2VHiTAyc2Ax3UgfGzRhkM+usCgYEAw5vbhKqZm5RXzm2y86NrYWNDulObzhEe" +
                    "9Hp/mNyVceMlS7Uh2g0C/lNNZ/pCvvzbgQLkawVtrH4RWmuco3XIb3HYTRAYiyOk" +
                    "80o6Vfitq8ojNvv0LPOOklMoNyYbGv9mF1PTfbWd/FhDI9UPWlb9XJmgbgzeWqxj" +
                    "kq2iswJHgXsCgYEAzhumtp0Ud/R3wPrt6Ald9i4MfNgsGrDwxHmZ7ZKBFLJRgnMp" +
                    "eL6RZSkUZ3Vcp9zDSRduMIIKBQsZJWkmtbkjE/DtFbF19QzLoyWnP5BFEgX/+VeK" +
                    "PAgvgVWZYW9lwf8EyPM0jYldfZ8jf++13uC6zmXaOw9rWVVDhGv06XL4rkECgYAa" +
                    "iwKtrOytJOMJWu9mii1fCLY/CWth+gbGS/0WhBAvZRBBhrGyQgO++RTO4DHActVi" +
                    "Wz8dmp34Qx8dsu6Na8UoAj3Er0N1Vf/jF5Z05grC/enrYyMOGnqlNm2FlkElV7TF" +
                    "w5U2QLfQbzWlHhs4OgelUh9n740Ypr3YQIGMYunLEwKBgCLZw0PtvdpQ24vOv+ns" +
                    "O8pEoQyQq2qkRx1RCkXwOav5zyON+g7fKF6zpXqZe0GHfGzdrhEjPhNNwEN/80hP" +
                    "Ch31uW4NDVSpRwZjUsF4ecoqiqJPTAXKpX8UTo2W4APP7upRDrEt5u6yRfxjwm6t" +
                    "mGt7MZZknZI8T6GylRAA3tTg";

    protected String publicKey =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo3m+TBY5yPl6Hs+cIcNH" +
                    "GRtI8zWlSsaQFiTz5Ki9s3zr0tEphyCDE/fPB84dOAXcbfFlDFr9Fvwb8x9/WU6U" +
                    "86xoLcfg056mTdCEQyqM0wxAQ9pUn6zKVMunU68LAwk0fryEmmBvHGndkKKHk5kU" +
                    "aqJKmE8Hv2LDP8YabA7ZlR2EWnoczytt/0/KvYgSF4FN9ZFUsIa0SnoM5sQ1/VXv" +
                    "I0QhC/sTf3T5igbxQOls+be4wCWfnZnQamkbkaX35MHmdc8vgm7xNI9Ji+XXRHKP" +
                    "/qf7i+CNai4cUL2bld8dXQOaLuw5hKnlauKOh1HhWcJbxOF/qc3PT6LQxaouANn5" +
                    "6QIDAQAB";

    @Test
    public void testDecrypt() {

        String str = "F3PwrUMoUvWtiBnMpDaLrlNAfpTnHVsElcFhmC8AMVcFA7Ie4ugtl7ZyUPfiuuHauqKAf7Osz3XTwqEEQj0NTgj+kLgorznsCa8gJxOLRaWpPJy873OS9ouJwAzxqC3L+fCpVCdXamVEeaMkXXkOujGpc4xuJ2FD1VSjejCNKOB6BlUOePPcnVv+8llZDzPw4UTHkIGPmAz8Iz1hFPxo0qAQAUJLW2G+ds7YbP8lgZpiqw+f91YH2WL8PUju2U6RHrG0DKo4+2NIHk+d4qCTo6tmcKdrIz6VnvhzFNR8oTyL2oyn3/sah7CLkS7VZ6TOOpNIKL+lgdmQU21wxXRW3g==";

        String outStr = null;

        try {
            outStr = RSAEncrypt.decrypt(str, this.privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertEquals("test", outStr);
    }


    @Test
    public void testEncryptAndDecrypt() {

        String outStr = null;
        String str = null;

        try {
            outStr = RSAEncrypt.encrypt("test", this.publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertNotNull(outStr);

        try {
            str = RSAEncrypt.decrypt(outStr, this.privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertEquals("test", str);
    }

    @Test
    public void testDecrypt2() {

        String str = "F3PwrUMoUvWtiBnMpDaLrlNAfpTnHVsElcFhmC8AMVcFA7Ie4ugtl7ZyUPfiuuHauqKAf7Osz3XTwqEEQj0NTgj+kLgorznsCa8gJxOLRaWpPJy873OS9ouJwAzxqC3L+fCpVCdXamVEeaMkXXkOujGpc4xuJ2FD1VSjejCNKOB6BlUOePPcnVv+8llZDzPw4UTHkIGPmAz8Iz1hFPxo0qAQAUJLW2G+ds7YbP8lgZpiqw+f91YH2WL8PUju2U6RHrG0DKo4+2NIHk+d4qCTo6tmcKdrIz6VnvhzFNR8oTyL2oyn3/sah7CLkS7VZ6TOOpNIKL+lgdmQU21wxXRW3g==";

        String outStr = null;


        String privateKey =
                "-----BEGIN PRIVATE KEY-----\n" +
                        "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCjeb5MFjnI+Xoe\n" +
                        "z5whw0cZG0jzNaVKxpAWJPPkqL2zfOvS0SmHIIMT988Hzh04Bdxt8WUMWv0W/Bvz\n" +
                        "H39ZTpTzrGgtx+DTnqZN0IRDKozTDEBD2lSfrMpUy6dTrwsDCTR+vISaYG8cad2Q\n" +
                        "ooeTmRRqokqYTwe/YsM/xhpsDtmVHYRaehzPK23/T8q9iBIXgU31kVSwhrRKegzm\n" +
                        "xDX9Ve8jRCEL+xN/dPmKBvFA6Wz5t7jAJZ+dmdBqaRuRpffkweZ1zy+CbvE0j0mL\n" +
                        "5ddEco/+p/uL4I1qLhxQvZuV3x1dA5ou7DmEqeVq4o6HUeFZwlvE4X+pzc9PotDF\n" +
                        "qi4A2fnpAgMBAAECggEBAIMgLBwr1731HpeIP9x1Hpps4F71MNiB3SFmEpvfHgrS\n" +
                        "sPYw//Z7haiGpXHFSnqdeOpXLo5yjX/aSTECmsuv1JqAODBAm34jS5IvJ2gYwUyo\n" +
                        "DwReJHLPzpuln1nolhShVZIy9Mo/f+Byql5RgB5MM6w08VCqU7SYK7UD2j3Cy5Rn\n" +
                        "CDdf2N3mluNtcZie8/SJR6A1QkhlGfmTWrK4wXugf95qzppIwn9eP52n42E1mYa7\n" +
                        "bk2yBoNqTRhOv2AqWyC7daOJ5tvbaa9N2ATabepncL98ISS0kGcNKzBEHA6sqYEv\n" +
                        "o3KMp7C2oBA5jl7BkQrYKuOX6dFUJlK2AqVb9fIE2WECgYEA1fIzdKmAJnFWPfeg\n" +
                        "YpNsFM8YLY/NFYbbwaGqAPVrdqvyM2aNWQrPfO2tzfSZObZ53Ev/xc6sFFOutnCs\n" +
                        "b3S/TpSzFYhqjXDByKqJKyxDnCLYrr22HvSg7j6mC7aW+MDWpDus9kKbeIaOl+Mn\n" +
                        "X7f2VHiTAyc2Ax3UgfGzRhkM+usCgYEAw5vbhKqZm5RXzm2y86NrYWNDulObzhEe\n" +
                        "9Hp/mNyVceMlS7Uh2g0C/lNNZ/pCvvzbgQLkawVtrH4RWmuco3XIb3HYTRAYiyOk\n" +
                        "80o6Vfitq8ojNvv0LPOOklMoNyYbGv9mF1PTfbWd/FhDI9UPWlb9XJmgbgzeWqxj\n" +
                        "kq2iswJHgXsCgYEAzhumtp0Ud/R3wPrt6Ald9i4MfNgsGrDwxHmZ7ZKBFLJRgnMp\n" +
                        "eL6RZSkUZ3Vcp9zDSRduMIIKBQsZJWkmtbkjE/DtFbF19QzLoyWnP5BFEgX/+VeK\n" +
                        "PAgvgVWZYW9lwf8EyPM0jYldfZ8jf++13uC6zmXaOw9rWVVDhGv06XL4rkECgYAa\n" +
                        "iwKtrOytJOMJWu9mii1fCLY/CWth+gbGS/0WhBAvZRBBhrGyQgO++RTO4DHActVi\n" +
                        "Wz8dmp34Qx8dsu6Na8UoAj3Er0N1Vf/jF5Z05grC/enrYyMOGnqlNm2FlkElV7TF\n" +
                        "w5U2QLfQbzWlHhs4OgelUh9n740Ypr3YQIGMYunLEwKBgCLZw0PtvdpQ24vOv+ns\n" +
                        "O8pEoQyQq2qkRx1RCkXwOav5zyON+g7fKF6zpXqZe0GHfGzdrhEjPhNNwEN/80hP\n" +
                        "Ch31uW4NDVSpRwZjUsF4ecoqiqJPTAXKpX8UTo2W4APP7upRDrEt5u6yRfxjwm6t\n" +
                        "mGt7MZZknZI8T6GylRAA3tTg\n" +
                        "-----END PRIVATE KEY-----";
        try {
            outStr = RSAEncrypt.decrypt(str, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assertions.assertEquals("test", outStr);
    }
}
