package cn.mediinfo.springdemo.enums;


/**
 * 枚举高级用法-实现虚方法
 * 使用场景：策略模式、工厂模式、抽象方法集合
 */
public enum AbstractMethod {
    google{
        @Override
        public String getBuilder(String registrationId) {
            return String.format("%s %s","google枚举返回值：",registrationId);
        }
    },
    github{
        @Override
        public String getBuilder(String registrationId) {
            return String.format("%s %s","github枚举返回值：",registrationId);
        }
    };


    /**
     * 一个构建的虚方法
     * @param registrationId
     * @return
     */
    public abstract String getBuilder(String registrationId);
}
