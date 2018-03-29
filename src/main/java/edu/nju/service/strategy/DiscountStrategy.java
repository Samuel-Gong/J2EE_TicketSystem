package edu.nju.service.strategy;

/**
 * @author Shenmiu
 * @date 2018/03/25
 * <p>
 * 根据会员等级指定折扣的类，规则在类中定义好
 */
public class DiscountStrategy {

    private final static int[] LEVELS = {0, 1, 2, 3, 4, 5};
    private final static int[] DISCOUNT = {10, 9, 8, 7, 6, 5};

    /**
     * 根据会员的等级计算折扣
     *
     * @param level 会员等级
     * @return 该会员等级所对应的折扣
     */
    public static Integer calculateDiscount(int level) {
        return DISCOUNT[findIndex(level)];
    }

    private static int findIndex(int level) {
        int index = 0;
        while (LEVELS[index] != level) {
            index++;
        }
        return index;
    }
}
