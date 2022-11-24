import java.util.HashMap;
import java.util.Stack;

public class ParenthesesChecker {
    private Stack<Character> parenthesesStack;
    private int normalBraceCount;
    private HashMap<Character,Integer> braceHashmap;
    public ParenthesesChecker() {
        parenthesesStack = new Stack<>();
        normalBraceCount = 0;
        braceHashmap = new HashMap<>();
        braceHashmap.put('(', 0);
        braceHashmap.put(')', 0);
        braceHashmap.put('[', 0);
        braceHashmap.put(']', 0);
        braceHashmap.put('{', 0);
        braceHashmap.put('}', 0);
        braceHashmap.put('<', 0);
        braceHashmap.put('>', 0);
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


    public boolean checkParentheses(String testString){
        for (char c :
                testString.toCharArray()) {
            if(!Character.isLetter(c)&&(c!=' ')){
                parenthesesStack.push(c);
                normalBraceCount+=1;
            }
        }


        if (normalBraceCount % 2==1){
            return false;
        } else if (normalBraceCount==0){
            return true;
        } else {
            //(()())->true
            // ((() -> false rightcounter =1   leftcounter = 2
            // loop through the stack
            // if its a right bracket -> increment the rightcounter
            // same for the left bracket -> increment the leftcounter
            // check if the leftcounter > right counter => return false
            //(()))) -> false
            // if right brackets == left brackets, return true, if not return false
            // (() -> false
            //(()))) - false
            Character previousCharacter=null;
            while(normalBraceCount>0){
                Character charToRemove = parenthesesStack.pop();
                braceHashmap.put(charToRemove,braceHashmap.get(charToRemove)+1);
                boolean isNormalLeftBigger = braceHashmap.get('(')>braceHashmap.get(')');
                boolean isSquareLeftBigger = braceHashmap.get('[')>braceHashmap.get(']');
                boolean isCurlyLeftBigger = braceHashmap.get('{')> braceHashmap.get('}');
                boolean isAngleLeftBigger = braceHashmap.get('<')>braceHashmap.get('>');
                if (isAngleLeftBigger||isCurlyLeftBigger||isSquareLeftBigger||isNormalLeftBigger) {
                    return false;
                }
                if (previousCharacter!=null&&
                        isLeftBracket(charToRemove)&&
                !isLeftBracket(previousCharacter)&&
                        !charToRemove.equals(findLeftBracket(previousCharacter))){
                    return false;
                }
                previousCharacter = charToRemove;
                normalBraceCount-=1;
// (((())))))) -> false because left bigger than right

            }

            boolean isNormalLeftEquals = braceHashmap.get('(')==braceHashmap.get(')');
            boolean isSquareLeftEquals = braceHashmap.get('[')==braceHashmap.get(']');
            boolean isCurlyLeftEquals = braceHashmap.get('{')== braceHashmap.get('}');
            boolean isAngleLeftEquals = braceHashmap.get('<')==braceHashmap.get('>');
            if (!(isAngleLeftEquals&&isCurlyLeftEquals&&isSquareLeftEquals&&isNormalLeftEquals)){
                return false;
            }
        }
        return true;

    }
}
