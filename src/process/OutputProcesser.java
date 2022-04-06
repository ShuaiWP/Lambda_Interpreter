package process;

import util.Utilities;

/**
 * @author IDo!
 * @version 1.0
 */
public class OutputProcesser {
    /**
     *输出处理
     */
    public static String outputProcess(String result){
        //判断是否为邱奇数
        if (Utilities.is_Church(result)){
            return Utilities.num_StringToInteger(result) + "";
        }
        if(result.equals("\\f.\\x.x")){
            return "0";
        }

        //判断是否为true和false
        if(result.equals("\\x.\\y.x")){
            return "true";
        }
        if(result.equals("\\x.\\y.y")){
            return "false";
        }

        return result;
    }
}
