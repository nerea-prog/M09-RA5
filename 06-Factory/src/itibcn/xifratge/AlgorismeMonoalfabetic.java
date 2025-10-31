package itibcn.xifratge;

public abstract class AlgorismeMonoalfabetic extends AlgorismeFactory {
    @Override
    public Xifrador creaXifrador(){
        return new XifradorMonoalfabetic();
    }

}
