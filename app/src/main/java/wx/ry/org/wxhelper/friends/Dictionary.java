package wx.ry.org.wxhelper.friends;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import wx.ry.org.wxhelper.CodeLog;

/**
 * Created by renyang on 16/2/16.
 */
public class Dictionary {

    private String[] strExpression,strChinese,strEnglish,strAdjective,strNoun,strNick;

    public Dictionary(Context context){
        try{
            //expression
            String expressionData = load(context,"expression_data.txt","gb2312");
            CodeLog.i("expressionData:" + expressionData);
            strExpression = expressionData.split("&");
            CodeLog.i("strExpression:" + strExpression.length);
            //chinese
            String chineseData = load(context,"chinese_data.txt","gb2312");
            CodeLog.i("chineseData:"+chineseData);
            strChinese = chineseData.split("&");
            CodeLog.i("strChinese:"+strChinese.length);
            //english
            String englishData = load(context,"english_data.txt","gb2312");
            CodeLog.i("englishData:"+englishData);
            strEnglish = englishData.split("&");
            CodeLog.i("strEnglish:"+strEnglish.length);
            //adjective
            String adjectiveData = load(context,"adjective_data.txt","gb2312");
            CodeLog.i("adjectiveData:"+adjectiveData);
            strAdjective = adjectiveData.split("&");
            CodeLog.i("strAdjective:"+strAdjective.length);
            //noun
            String nounData = load(context,"noun_data.txt","gb2312");
            CodeLog.i("nounData:"+nounData);
            strNoun = nounData.split("&");
            CodeLog.i("strNoun:"+strNoun.length);
            //nick
            String nickData = load(context,"nick_data.txt","gb2312");
            CodeLog.i("nickData:"+nickData);
            strNick = nickData.split("&");
            CodeLog.i("strNick:"+strNick.length);
        }catch (IOException e){

        }
    }

    public List<String> getNickName(int num){
        List<String> list = new ArrayList<String>();
        for(int i = 0; i< num ; i++){
            int temp = getRandomNum(100);
            if(temp < 30){
                list.add(strNick[getRandomNum(strNick.length)]);
            }else if(temp <70){
                list.add(strAdjective[getRandomNum(strAdjective.length)]+"的"+strNoun[getRandomNum(strNoun.length)]);
            }else if(temp < 90){
                list.add(strChinese[getRandomNum(strChinese.length)]);
            }else{
                list.add(strEnglish[getRandomNum(strChinese.length)]);
            }
        }
        return list;
    }

    private String load(Context context,String path,String charsetName) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(context.getAssets().open(path),charsetName));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line=r.readLine())!=null){
            stringBuilder.append(line);
        }
        r.close();
        return stringBuilder.toString();
    }

    private int getRandomNum(int length){
        return (int) (Math.random() * length);
    }
}

