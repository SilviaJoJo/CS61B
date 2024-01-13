public class AltList <X, Y>{
    private X item;
    private AltList<Y, X> next;
    AltList(X item, AltList<Y, X> next) {
        this.item = item;
        this.next = next;
    }

    public AltList<Y, X> pairsSwapped() {
        if (this.next.next == null) {
            return new AltList<Y, X>(this.next.item, new AltList<X, Y> (this.item, null));
        }
        AltList<Y, X> toDo = this.next.next.pairsSwapped();
        return new AltList<Y, X>(this.next.item, new AltList<X, Y> (this.item, toDo));
    }

    public static void main(String[] args) {
        AltList<Integer, String> list = new AltList<Integer, String>(5,
                new AltList<String, Integer>("cat",
                        new AltList<Integer, String>(10,
                                new AltList<String, Integer>("dog", null))));
        AltList<String, Integer> result = list.pairsSwapped();
        System.out.println(result.item);
        System.out.println(result.next.item);
        System.out.println(result.next.next.item);
        System.out.println(result.next.next.next.item);
    }
}
