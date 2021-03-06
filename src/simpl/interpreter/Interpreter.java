package simpl.interpreter;

import java.io.FileInputStream;
import java.io.InputStream;

import simpl.parser.Parser;
import simpl.parser.Symbol;
import simpl.parser.SyntaxError;
import simpl.parser.ast.Expr;
import simpl.typing.DefaultTypeEnv;
import simpl.typing.TypeError;

public class Interpreter {

    public int skip;

    public void run(String filename, int skip) {
        try (InputStream inp = new FileInputStream(filename)) {
            Parser parser = new Parser(inp);
            java_cup.runtime.Symbol parseTree = parser.parse();
            Expr program = (Expr) parseTree.value;
            System.out.println(program.toString());
            if (skip == 0) {
                System.out.print("Type: ");
                System.out.println(program.typecheck(new DefaultTypeEnv()).t);
            }
            System.out.print("Value: ");
            System.out.println(program.eval(new InitialState()));
            System.out.println("");
        }
        catch (SyntaxError e) {
            System.out.print("syntax error: ");
            System.out.println(e.toString());
            System.out.println("");
        }
        catch (TypeError e) {
            System.out.print("type error: ");
            System.out.println(e.toString());
            System.out.println("");
        }
        catch (RuntimeError e) {
            System.out.println("runtime error");
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void interpret(String filename) {
        Interpreter i = new Interpreter();
        System.out.println(filename);
        i.run(filename, 0);
    }

    private static void interpret(String filename, int skip) {
        Interpreter i = new Interpreter();
        System.out.println(filename);
        i.run(filename, skip);
    }

    public static void main(String[] args) {
        interpret("doc/examples/plus.spl");
        interpret("doc/examples/factorial.spl");
        interpret("doc/examples/gcd1.spl");
        interpret("doc/examples/gcd2.spl");
        interpret("doc/examples/max.spl");
        interpret("doc/examples/sum.spl");
        interpret("doc/examples/map.spl");
        interpret("doc/examples/pcf.sum.spl");
        interpret("doc/examples/pcf.even.spl");
        interpret("doc/examples/pcf.minus.spl");
        interpret("doc/examples/pcf.factorial.spl");
        interpret("doc/examples/pcf.fibonacci.spl");
        interpret("doc/examples/pcf.twice.spl");
        interpret("doc/examples/pcf.lists.spl");
        interpret("doc/examples/define.spl", 1);
        interpret("doc/examples/gc.spl");
    }
}
