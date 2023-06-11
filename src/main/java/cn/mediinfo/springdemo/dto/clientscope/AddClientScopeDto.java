package cn.mediinfo.springdemo.dto.clientscope;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@NoArgsConstructor(force = true) //自动生成无参造函数
public class AddClientScopeDto {

    /**
     * scope 名称
     */
    @NotNull("scope不能为空")
    @Size(min = 1,max = 2,message = "scope长度应在2到20之间")
    private String scope;

    /**
     * clientId
     */
    @NotNull
    @Min(value = 1, message = "clientId不能小于1")
    @Max(value = 64, message = "clientId不能大于64")
    private String clientId;
}
