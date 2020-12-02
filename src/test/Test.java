package test;

import com.desk.util.CharUtil;
import com.desk.util.DifferenceUtil;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        String s = "sgsetYhwryjtwu";
        System.out.println(CharUtil.captureName(s));

        List<Data> origin = Test.createList(0,5);
        List<Data> newData = Test.createList(3,7);
        DifferenceUtil<Data> diff = new DifferenceUtil<>();
        List<Data> remove = diff.different(origin, newData, Data.class, new String[]{ "id", "part2"});
        List<Data> add = diff.different(newData, origin, Data.class, new String[]{"id", "part2"});
        remove.forEach(da -> {
            System.out.println(da.getId() +" "+da.getDetailId()+" "+da.getPart1()+" "+da.getPart2());
        });
        System.out.println("----------------------------------------------------------------------------");
        add.forEach(da -> {
            System.out.println(da.getId() +" "+da.getDetailId()+" "+da.getPart1()+" "+da.getPart2());
        });
        System.out.println("******************************************************************************");
        origin.forEach(da -> {
            System.out.println(da.getId() +" "+da.getDetailId()+" "+da.getPart1()+" "+da.getPart2());
        });
    }

    public static List<Data> createList(int begin, int end){
        List<Data> list = new ArrayList<>();
        for (int i = begin; i < end; i++) {
            Data sx = new Data();
            sx.setId(i);
            sx.setDetailId(i);
            sx.setPart1(i+"");
            sx.setPart2(i+"");
            list.add(sx);
        }
        return list;
    }


    static class Data{
        private Integer id;

        private Integer detailId;

        private String part1;

        private String part2;

        public void setId(Integer id){
            this.id = id;
        }

        public void setDetailId(Integer detailId){
            this.detailId = detailId;
        }
        public void setPart1(String part1){
            this.part1 = part1;
        }
        public void setPart2(String part2){
            this.part2 = part2;
        }

        public Integer getId(){
            return this.id;
        }
        public Integer getDetailId(){
            return this.detailId;
        }

        public String getPart1() {
            return part1;
        }

        public String getPart2() {
            return part2;
        }
    }
}
