package edu.nju.service.strategy;

/**
 * @author Shenmiu
 * @date 2018/03/25
 * <p>
 * 计算会员等级的类，规则在该类中定义好
 */
public class LevelStrategy {

    private static int[] points = {500, 1000, 2000, 5000, 10000};
    private static int[] levels = {0, 1, 2, 3, 4, 5};

    /**
     * 根据会员积分计算等级
     *
     * @param point 会员积分
     * @return 会员等级
     */
    public static Integer calculateLevel(int point) {
        int index = 0;
        while (point > points[index]) {
            index++;
        }
        return levels[index];
    }
}
