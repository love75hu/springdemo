package cn.mediinfo.springdemo.context.datasource;

public class DataSorceContextHolder {
    private static final ThreadLocal<String> prefixHolder = new ThreadLocal<>();

    public static void setPrefix(String prefix) {
        prefixHolder.set(prefix);
    }

    public static String getPrefix() {
        return prefixHolder.get();
    }

    public static void clearPrefix() {
        prefixHolder.remove();
    }
}
