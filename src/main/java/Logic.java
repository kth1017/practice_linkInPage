import java.io.*;

public class Logic {
    public static void main(String[] args) throws IOException {

        // 텍스트 불러오기
        FileReader reader = new FileReader("D:\\input.txt");

        String inputText = "";
        int charTxt;
        while ((charTxt = reader.read()) != -1) {
            inputText += (char)charTxt;
        }


        // 인덱스 개수
        int startIndex = inputText.indexOf("Index");
        String startText = inputText.substring(startIndex);

        String countTitleKeyword = "blockquote data-ke-size=\"size16\" data-ke-style=\"style1\">";
        int count = 0;

        String[] startTextArrBySpace = startText.split("<");
        int textLen = startTextArrBySpace.length;
        for (int i = 0; i < textLen; i++) {
            if (startTextArrBySpace[i].equals(countTitleKeyword)) {
                count++;
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
        System.out.println(resultArr[1]);
        String resultText = "";






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

            pw.write(addTitleText);
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
