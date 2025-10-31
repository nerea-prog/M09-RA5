package itibcn.xifratge;

public abstract class AlgorismeAES extends AlgorismeFactory {
    @Override
    public Xifrador creaXifrador(){
        return new XifradorAES();
    }
}