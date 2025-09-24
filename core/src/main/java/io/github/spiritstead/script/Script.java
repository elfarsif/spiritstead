package io.github.spiritstead.script;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Script {

    HashMap<Integer, ArrayList<String>> chapter1 = new HashMap<>();

    public Script() {
        loadScript();
    }

    // in script file '//' will be interpreted as comments
    private void loadScript() {
        try {
            InputStream is = getClass().getResourceAsStream("/script/script.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            int slideNum = 1;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                //check that line isnt commented out
                if (!line.isEmpty() && line.length() >= 2 && line.substring(0, 2).equals("//")) {
                    continue;
                }

                int currentSlide = Integer.parseInt(line.substring(0, 1));
                if (chapter1.get(currentSlide) == null) {
                    //remove the number and dot
                    String temp = line.substring(2);
                    line = temp.trim();
                    chapter1.put(currentSlide, new ArrayList<>(Arrays.asList(line)));
                } else {
                    ArrayList<String> currentLines = chapter1.get(currentSlide);

                    //remove the number and dot
                    String temp = line.substring(2);
                    line = temp.trim();
                    currentLines.add(line);
                    chapter1.put(currentSlide, currentLines);
                }
            }

            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }

    public HashMap<Integer, ArrayList<String>> getChapter1() {
        return chapter1;
    }
}
