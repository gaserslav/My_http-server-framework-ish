package joca;

//ovo je self sizing array kontas niz koji sam siri
public class selfsizearray<T> {
        private T[] niz;

        public selfsizearray(){
            niz=(T[])new Object[0];
        }

    public selfsizearray(T[] star){
        niz=star.clone();
    }

        //vraca velicinu
        public int length(){
            return niz.length;
        }

        //vraca concretno
        public T get(int index){
            return niz[index];

        }
        public void changeinto(selfsizearray<T> s){
            niz=s.toArray();
        }

        //ovo prosirava niz
        public void appand(T novo){


            //ovo pravi novi niz za 1 duzi od starog i u podpunosti ga kopira
            ;
            T[] noviniz= (T[]) new Object[niz.length+1];
            for(int i=0;i<niz.length;i++){
                noviniz[i]=niz[i];
            }
            //dodaje novi clan niza
            noviniz[niz.length]=novo;


            // i sad zamenjuje stari niz
            niz=noviniz;

        }

        public T[] toArray(){
            return niz;
        }




}
