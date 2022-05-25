import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Logic {
    public static void main(String[] args) throws IOException {

        // 텍스트 불러오기
        FileReader reader = new FileReader("D:\\input.txt");

        String inputText = "";
        int charTxt;
        while ((charTxt = reader.read()) != -1) {
            inputText += (char)charTxt;
        }


        // 인덱스 개수 + 타이틀 저장
        int startIndex = inputText.indexOf("Index");
        String startText = inputText.substring(startIndex);

        String countTitleKeyword = "blockquote data-ke-size=\"size16\" data-ke-style=\"style1\">";
        int count = 0;

        String[] startTextArrBySpace = startText.split("<");
        int textLen = startTextArrBySpace.length;
        List<String> oldTitleList = new ArrayList<>();
        for (int i = 0; i < textLen; i++) {
            if (startTextArrBySpace[i].equals(countTitleKeyword)) {
                count++;
                // 타이틀명 스트림으로 저장
                oldTitleList.add(startTextArrBySpace[i+1]);
            }
        }
        // 타이틀별 id 추가

        String splitTitleKeyword = "blockquote data-ke-size=\"size16\" data-ke-style=\"style1\">";
        String[] inputTextArrByBrak =  inputText.split(splitTitleKeyword);
        String addTitleText = inputTextArrByBrak[0];

        int arrLen = inputTextArrByBrak.length;
        for (int i = 1; i < arrLen; i++) {
            addTitleText += "blockquote data-ke-size=\"size16\" data-ke-style=\"style1\" "+"id=\""+i+"th\">";
            addTitleText += inputTextArrByBrak[i];
        }

        // index 작성
        String[] resultArr = addTitleText.split("Index");
        String resultText = resultArr[0]+"Index";

        List<String> titleList = new ArrayList<>();
        for (int i = 0; i < oldTitleList.size(); i++) {
            titleList.add(oldTitleList.get(i).split(">")[1]);;
        }

        for (int i = 0; i < titleList.size(); i++) {
            int j = i+1;
            resultText += "<br /><a href=\"#"+j+"th\">"+j+" "+titleList.get(i)+"</a>";
        }
        resultText += resultArr[1];

        // reult.txt 생성
        try {
            File file = new File("D:\\result.txt");

            try {
                if (file.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File already exists");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);

            pw.write(resultText);
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
