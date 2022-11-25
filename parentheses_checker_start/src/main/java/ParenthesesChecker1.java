import java.util.Stack;

public class ParenthesesChecker1 {
    public Stack<Character> myStack;
    public ParenthesesChecker1(){
        myStack = new Stack<>();
    }
    public boolean isLeftBracket(char bracket){
        switch (bracket){
            case '}':
                return false;
            case '>':
                return false;
            case ']':
                return false;
            case ')':
                return false;
            default:
                return true;
        }
    }

    public Character findLeftBracket(char bracket){
        switch (bracket){
            case '}':
                return '{';
            case '>':
                return '<';
            case ']':
                return '[';
            case ')':
                return '(';
            default:
                return bracket;
        }
    }

    public boolean checkParentheses(String testString){
        for (char c :
                testString.toCharArray()) {
            if (isLeftBracket(c)&&!Character.isLetter(c)&&c!=' ' ) {
                myStack.push(c);
            }
            if (!isLeftBracket(c)&&!Character.isLetter(c)&&c!=' '){
                try{
                    Character charRemoved = myStack.pop();
                    if (charRemoved!=findLeftBracket(c)){
                        return false;
                    }
                } catch (Exception e){
                    return false;
                }
            }
        }
        if (!myStack.empty()){
            return false;
        }
        return true;
    }

}
