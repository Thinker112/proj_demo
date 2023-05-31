package EnumTest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class User {
    private Long id;
    private String name;
    /**
     * 会员身份
     * 1：Gold，6折
     * 2：Silver，7折
     * 3：Bronze，8折
     */
    private Integer memberType;
}

