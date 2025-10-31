package itibcn.xifratge;

public abstract class AlgorismePolialfabetic extends AlgorismeFactory {
    @Override
    public Xifrador creaXifrador(){
        return new XifradorPolialfabetic();
    }

}
