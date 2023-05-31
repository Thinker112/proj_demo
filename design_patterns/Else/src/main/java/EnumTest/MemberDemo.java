package EnumTest;

import java.math.BigDecimal;

public class MemberDemo {
    public static void main(String[] args) {
        User user = new User(1L, "bravo", MemberEnum.GOLD_MEMBER.getType());
        BigDecimal productPrice = new BigDecimal("1000");
        BigDecimal discountedPrice = calculateFinalPrice(productPrice, user.getMemberType());
        System.out.println(discountedPrice);
    }

    /**
     * 根据会员身份返回折扣后的商品价格
     *
     * @param originPrice
     * @param type
     * @return
     */
    public static BigDecimal calculateFinalPrice(BigDecimal originPrice, Integer type) {
        return MemberEnum.getEnumByType(type).calculateFinalPrice(originPrice);
    }
}
