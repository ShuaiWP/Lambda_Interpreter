import interpreter.Interpreter;
import lexer.Lexer;
import parser.AST;
import parser.Parser;
import process.OutputProcesser;
import process.Preprocesser;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author IDo!
 * @version 1.0
 */
public class main {
    public static void main(String[] args) {

        System.out.println("Welcome to the world of lambda calculus" +
                "\nFunctional programming is fascinating" +
                "\nEnter \"quit\" to exit" +
                "\n-----------------------------------------------------------------");

        //主循环体
        Preprocesser.initialize();
        boolean inloop = true;
        Scanner scanner = new Scanner(System.in);
        while(inloop){
            System.out.print(">>>");
            String exp = scanner.nextLine();
            if(exp.equalsIgnoreCase("quit")){
                inloop = false;
                System.out.println("QUIT SUCCESS");
                continue;
            }

            exp = Preprocesser.preprocess(exp);

            if(!exp.equals("***")) {
                Lexer lexer = new Lexer(exp);
                Parser parser = new Parser(lexer);
                AST ast = parser._Set();
                Interpreter interpreter = new Interpreter();
                AST newast = interpreter.cal(ast);
                String line = newast.finaltoString(new ArrayList<String>());

                line = OutputProcesser.outputProcess(line);
                System.out.println(line);
            }else {
                System.out.println("DEFINE SUCCESS");
            }

//            System.out.println(Utilities.num_StringToInteger(line));
        }


//        System.out.println(Preprocess.num_IntegerToString(Integer.parseInt(exp)));
//        String test = "(\\m.\\n. n m)(" + Preprocess.num_IntegerToString(2) +")("+
//                Preprocess.num_IntegerToString(4) +")";
//
//        String source = "(\\x.\\y.x)(\\x.x)(\\y.y)";
//        String source = "(\\m.\\n. n m)(\\a.\\x.(a(a (a x))))(\\a.\\x.(a(a (a (a x))))";
//        (\m.\n. n m)(\a.\x.(a(a (a x))))(\a.\x.(a(a (a (a x))))
//        String one = "(\\n.\\f.\\x.f (n f x))(\\f.\\x.x)";
//        String two = "(\\n.\\f.\\x.f (n f x))((\\n.\\f.\\x.f (n f x))(\\f.\\x.x))";
//        String three = "(\\n.\\f.\\x.f (n f x))((\\n.\\f.\\x.f (n f x))((\\n.\\f.\\x.f (n f x))(\\f.\\x.x)))";
//        iszero: \n.n (\x.(\a.\b.b)) (\a.\b.a)
    }
}
