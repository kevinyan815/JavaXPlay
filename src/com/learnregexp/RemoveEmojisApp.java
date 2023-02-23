package com.learnregexp;

public class RemoveEmojisApp {

    // 这个例子的思路是，先创建字符串里的字符白名单，然后在取反，这样就不用担心遗漏emoji
    // Instead of blacklisting some elements, how about creating a whitelist of the characters you do wish to keep?
    // This way you don't need to worry about every new emoji being added.
    public static void main(String[] args) {
        // [\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s] is a range representing all numeric (\\p{N}),
        // letter (\\p{L}), mark (\\p{M}), punctuation (\\p{P}), whitespace/separator (\\p{Z}),
        // other formatting (\\p{Cf}) and other characters above U+FFFF in Unicode (\\p{Cs}),
        // and newline (\\s) characters. \\p{L} specifically includes the characters from other alphabets
        // such as Cyrillic, Latin, Kanji, etc.
        String characterFilter = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";

        String strWithEmotions = "hello world _# 皆さん、こんにちは！　你好世界。🔥";

        String emotionlessStr = strWithEmotions.replaceAll(characterFilter, "");
        System.out.println(emotionlessStr);
    }
}
