package EnumTest;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.function.Function;

enum MemberEnum {

    GOLD_MEMBER(1, "Gold", originPrice -> originPrice.multiply(new BigDecimal(("0.6")))),
    SILVER_MEMBER(2, "Silver", originPrice -> originPrice.multiply(new BigDecimal(("0.7")))),
    BRONZE_MEMBER(3, "Bronze", originPrice -> originPrice.multiply(new BigDecimal(("0.8"))));

    // ---------- 下面才是MemberEnum类的定义 ---------
    @Getter
    private final Integer type;
    @Getter
    private final String desc;
    private final Function<BigDecimal, BigDecimal> calculateFinalPrice;

    MemberEnum(Integer type, String desc, Function<BigDecimal, BigDecimal> calculateFinalPrice) {
        this.type = type;
        this.desc = desc;
        this.calculateFinalPrice = calculateFinalPrice;
    }

    public BigDecimal calculateFinalPrice(BigDecimal originPrice){
        return calculateFinalPrice.apply(originPrice);
    }

    public static MemberEnum getEnumByType(Integer type) {
        MemberEnum[] values = MemberEnum.values();
        for (MemberEnum memberEnum : values) {
            if (memberEnum.getType().equals(type)) {
                return memberEnum;
            }
        }
        throw new IllegalArgumentException("Invalid Enum type:" + type);
    }
}