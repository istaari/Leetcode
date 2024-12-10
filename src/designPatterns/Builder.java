package designPatterns;


/**
 * <h2>Problem:</h2>
 * <p>
 * You want to avoid a constructor with a long list of parameters, especially when some parameters are optional.
 * Using constructors with too many parameters can lead to code that is difficult to read and maintain.
 * </p>
 *
 * <h2>Solution:</h2>
 * <p>
 * The Builder pattern helps in creating complex objects step by step. It allows you to construct an object by only setting
 * the required attributes, and optional attributes can be added as needed. The pattern also helps in enforcing immutability
 * by building the object in a controlled manner.
 * </p>
 *
 * <h3>When to use:</h3>
 * <ul>
 *   <li><strong>Complex objects:</strong> When you need to create objects with many optional parameters, like configuring a database connection.</li>
 *   <li><strong>Immutable objects:</strong> When you want to ensure that an object is immutable and fully built before it is used.</li>
 *   <li><strong>Chained method calls:</strong> When creating an object step by step makes it easier to understand and maintain than using constructors with many arguments.</li>
 * </ul>
 *
 * <h3>Example usage:</h3>
 * <pre>
 * House house = new House.Builder()
 *                 .setFoundation("Concrete")
 *                 .setWalls("Brick")
 *                 .setRoof("Shingles")
 *                 .build();
 * </pre>
 *
 * <p>This class demonstrates the Builder design pattern.</p>
 */
class House0 {
    private final String foundation;
    private final String structure;
    private final String roof;

    private House0(HouseBuilder builder) {
        this.foundation = builder.foundation;
        this.structure = builder.structure;
        this.roof = builder.roof;
    }

    public static class HouseBuilder {
        private String foundation;
        private String structure;
        private String roof;

        public HouseBuilder setFoundation(String foundation) {
            this.foundation = foundation;
            return this;
        }

        public HouseBuilder setStructure(String structure) {
            this.structure = structure;
            return this;
        }

        public HouseBuilder setRoof(String roof) {
            this.roof = roof;
            return this;
        }

        public House0 build() {
            return new House0(this);
        }
    }
}


public class Builder {

    public static void main(String[] args) {
        House0 house = new House0.HouseBuilder().setFoundation("Concrete").setStructure("Wood").setRoof("Tiles").build();
    }

}
