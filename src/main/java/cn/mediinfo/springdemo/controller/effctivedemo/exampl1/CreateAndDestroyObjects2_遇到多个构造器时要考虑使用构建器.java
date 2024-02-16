package cn.mediinfo.springdemo.controller.effctivedemo.exampl1;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/*
 *@title CreateAndDestroyObjects2_遇到多个构造器时要考虑使用构建器
 *@description
 *@author thj
 *@create 2024-02-05
 */
public class CreateAndDestroyObjects2_遇到多个构造器时要考虑使用构建器 {
}

/**
 * 静态工厂和构建起都有共同的局限性，不能很好的扩展到大量的可选参数；
 * 例如：用一个类表示食品的营养成分，这个类有许多参数，有些是必须的，有些是可选的，有些是依赖于其他参数的，我们通常会提供构造器重叠的方式来满足这些需求，但是这样的构造器会很难阅读和编写，而且很难维护；
 * 示例代码：
 */
class NutritionFacts {
    /**
     * 服务大小
     */
    private final int servingSize;

    /**
     * 服务数量
     */
    private final int servings;

    /**
     * 卡路里
     */
    private final int calories;

    /**
     * 脂肪
     */
    private final int fat;

    /**
     * 钠
     */
    private final int sodium;

    /**
     * 碳水化合物
     */
    private final int carbohydrate;

    public NutritionFacts(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}

/**
 * 为了解决这个问题，可以使用构建器模式，构建器模式是一种对象的创建模式，它的目的是为了构建一个复杂对象，这个对象通常由多个部分组成，而构建器模式可以将构建对象的过程和表示对象的过程分离开来；
 * 示例代码：
 *         NutritionFacts2.Builder builder = new NutritionFacts2.Builder(240, 8).fat(100).calories(100).sodium(35).carbohydrate(27);
 *         NutritionFacts2 instance= builder.build();
 */

class NutritionFacts2{
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder{
        //必须参数
        private final int servingSize;
        private final int servings;

        //可选参数
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }


        /**
         * 需要对参数进行必要的检查
         * @return Builder
         */
        public Builder calories(int val){
            if (val < 0){
                //抛出异常，应该使用IllegalArgumentException异常
                throw new IllegalArgumentException("calories must be >= 0");
            }
            calories = val;
            return this;
        }

        public Builder fat(int val){
            fat = val;
            return this;
        }

        public Builder sodium(int val){
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val){
            carbohydrate = val;
            return this;
        }

        public NutritionFacts2 build(){
            return new NutritionFacts2(this);
        }
    }

    private NutritionFacts2(Builder builder){
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}

/**
 * Builder模式也适用于类层次结构，例如，假设我们有一个抽象类和一个具体的子类，我们想要让客户端使用Builder来构建子类的实例，我们可以在Builder中添加一个抽象的方法，然后在具体的Builder中实现这个方法；
 */
abstract class Pizza {
    public enum Topping {HAM, MUSHROOM, ONION, PEPPER, SAUSAGE}

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }
}

/**
 * 具体的子类1
 */
class NyPizza extends Pizza {
    public enum Size {SMALL, MEDIUM, LARGE}

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private NyPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }
}

/**
 * 具体的子类2
 */
class Calzone extends Pizza {
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false;

        public Builder sauceInside() {
            sauceInside = true;
            return this;
        }

        @Override
        Calzone build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }
}
