package process;

import util.Utilities;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author IDo!
 * @version 1.0
 */
public class Preprocesser {
    public static HashMap<String, String> keywords = new HashMap<>();

    /**
     * 关键字表初始化代码块
     */
    public static void initialize( ) {
        //算术运算
        keywords.put("_zero", "\\f.\\x.x");
        keywords.put("_succ", "\\n.\\f.\\x.f (n f x)");
        keywords.put("_plus", "\\m.\\n.m _succ n");
        keywords.put("_pow", "\\b.\\e.e b");
        keywords.put("_mult", "\\m.\\n.\\f.m (n f)");
        keywords.put("_pred", "\\n.\\f.\\x.n (\\g.\\h.h (g f)) (\\u.x) (\\u.u)");
        keywords.put("_sub", "\\m.\\n.n _pred m");
        //逻辑与谓词
        keywords.put("_true", "\\x.\\y.x");
        keywords.put("_false", "\\x.\\y.y");
        keywords.put("_and", "\\p.\\q.p q p");
        keywords.put("_or", "\\p.\\q.p p q");
        keywords.put("_not", "\\p.\\a.\\b.p b a");
        keywords.put("_if", "\\p.\\a.\\b.p a b");
        keywords.put("_iszero", "\\n.n (\\x._false) _true");
        keywords.put("_leq", "\\m.\\n._iszero (_sub m n)");
        keywords.put("_eq", "\\m.\\n. _and (_leq m n) (_leq n m)");
        //Y不动点组合子
        keywords.put("_Y", " \\g.(\\x.g (x x)) (\\x.g (x x))");
        //数据结构
        keywords.put("_cons", "\\x.\\y.\\f. f x y");
        keywords.put("_first", "\\p.p _true");
        keywords.put("_second", "\\p.p _false");
        keywords.put("_null", "\\x. _true");
        keywords.put("_isnull", "\\p.p (\\x.\\y._false)");

    }

    /**
     *预处理
     */
    public static String preprocess(String oriExp) {
        //将自定义组合子加入keywords中
        if(defKeyword(oriExp)){
            return "***";
        }
        //将数字转换成邱奇数
        String afterNum = numToChurch(oriExp);
        //进行关键字替换
        String afterKeyword = keywordToLambda(afterNum);

        return afterKeyword;
    }

    /**
     *判断是否为定义组合子语句
     *  如果是，则将组合子加入keywords中，并返回true；
     *  否则，返回false
     */
    public static boolean defKeyword(String exp){
        String[] exps = exp.split(" ");
        if (exps[0].equals("def")){
            String key = exps[1];
            int index = exps[0].length() + exps[1].length() + 2;
            String value = exp.substring(index);
            keywords.put(key,value);

            return true;
        }
        return false;
    }

    /**
     *将表达式中的所有自然数转换为邱奇数lambda表达式
     */
    public static String numToChurch(String exp){
        //如果没有数字
        if(!Utilities.hasDigit(exp)){
            return exp;
        }

        String resultExp = exp;
        while(Utilities.hasDigit(resultExp)){
            int num = Utilities.get_a_number(resultExp);
            String lambdaExp = "(" + Utilities.num_IntegerToString(num) + ")";
            resultExp = resultExp.replace(num+ "" , lambdaExp);
        }

        return resultExp;
    }

    /**
     *将表达式中的所有关键字转换为lambda表达式
     */
    public static String keywordToLambda(String oriExp){
        //如果没有关键字
        if (!oriExp.contains("_")) {
            return oriExp;
        }
        //如果有关键字
        String resultExp = oriExp;
        while (resultExp.contains("_")) {
            String which_keyword = Utilities.which_keyword(resultExp);
            String lambdaEpr = "(" + keywords.get(which_keyword) + ")";
            resultExp = resultExp.replace(which_keyword, lambdaEpr);
        }

        return resultExp;
    }
}
