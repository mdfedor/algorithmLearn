import java.util.LinkedList;
import java.util.List;

/*
* result = []
 def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)  //保存最终需要的结果
        return
    for 选择 in 选择列表:
        做选择                    //
        backtrack(路径, 选择列表) //递归调用
        撤销选择                 //选择列表恢复原样

        for 选择 in 选择列表:
    # 做选择
    将该选择从选择列表移除
    路径.add(选择)
    backtrack(路径, 选择列表)
    # 撤销选择
    路径.remove(选择)
    将该选择再加入选择列表

* 获得每个节点的路径和选择列表，当一个节点没有选择列表，他的路径就是最后的结果，把所有结果列出来，就是全排列
*
* */
public class backTracking {



    /*
     * @Description
     * @Date 9:57 2020/9/30
     * @Param [num] 一个int数组，这个是选择列表，存放所有选择的数字
     * @return java.util.List<java.util.List<int>>
     **/
    List<List<Integer>> res=new LinkedList<>();   //保存路径，也就是最后所有选择的排列

    public List<List<Integer>> getRes() {
        return res;
    }

    public static void main(String[] args) {

        backTracking bt=new backTracking();

        int[] num={1,2,3,4};

        bt.permute(num);

        List<List<Integer>> ret=bt.getRes();
        for(int i=0;i<ret.size();i++){
            System.out.println(ret.get(i));
        }
    }

    public List<List<Integer>> permute(int[] num){

        LinkedList<Integer> track=new LinkedList<>();
        backtrack(num,track);
        return res;
    }

    //节点有两个属性，一个是选择列表[1,2,3...n]  一个是存储排列结果，每个节点只能选一个数，也就是路径，随着节点下移，路径中(排列结果)会变多，选择列表会减少(下一个节点就不能使用上面已经选择过的数去进行排列)
    public void backtrack(int[] nums,LinkedList<Integer> track){
        //nums是选择列表，也就是使用哪几个数去排列，track存储路径，也就是数字的排列的结果
        if(track.size()==nums.length){//比如选择列表有三个数[1,2,3],那么最后排列的结果长度也是三位，那么这一组就是已经排完了
            res.add(new LinkedList(track));  //加到结果中,参数出栈会弹出，所以这里要new一份
            return;          //结束条件
        }
        //for循环中做选择
        for(int i=0;i<nums.length;i++){
            if(track.contains(nums[i]))  //路径中包含了选择列表中的数，说明这个数已经进行排列 比如选择列表[1,2,3],我排列的时候是[1],那么1就已经选择过了，第2位个第3位就不能再是1
                continue;
            track.add(nums[i]);  //num[1] num[2] num[3]  相当于是在排列

            backtrack(nums,track);  //下一个节点，这时候track里面已经有了n个排列
            track.removeLast();   //直到一个节点选择列表和路径长度一样，那么这个节点就是一个排列结果，因为i在控制排列中的第一位，第一位的排列可能有多个，特别是选择列表中数据多的时候，
                                  //那么此时撤销选择，也就是要把已经排完的路劲回退到上个节点还没排列的时候，直到回退到路径中的数=nums[i],i就可以++
                                  //这里有个地方要想一下，nums[0]的时候，下面backtrack递归中，会把nums[1]~nums[n]的所有组合进行排列，当i++的时候，i作为第一位的所有排列已经完成。
        }
    }
}
