package io.github.ititus.parser;

import java.util.Scanner;

public class ParserTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Parser<String, Integer> integer = Parsers.integer();

        while (true) {
            if (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(integer.parse(line));
            }
        }
    }
}
