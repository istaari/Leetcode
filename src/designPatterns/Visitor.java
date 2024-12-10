package designPatterns;


// Asset can be visited
interface Asset {
    void accept(TaxVisitor visitor);
}

// Visitor interface defines operations
interface TaxVisitor {
    double visit(House house);

    double visit(Car car);
}


// Different types of assets
class House implements Asset {
    private final double value;

    public House(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void accept(TaxVisitor visitor) {
        visitor.visit(this);
    }
}


class Car implements Asset {
    private final double value;

    public Car(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void accept(TaxVisitor visitor) {
        visitor.visit(this);
    }
}


// Concrete Visitor for Tax Calculation
class TaxCalculationVisitor implements TaxVisitor {
    @Override
    public double visit(House house) {
        // Specific tax calculation for house
        return house.getValue() * 0.01;
    }

    @Override
    public double visit(Car car) {
        // Specific tax calculation for car
        return car.getValue() * 0.02;
    }
}


// Another Visitor for Insurance Valuation
class InsuranceVisitor implements TaxVisitor {
    @Override
    public double visit(House house) {
        // Different calculation for house insurance
        return house.getValue() * 0.005;
    }

    @Override
    public double visit(Car car) {
        // Different calculation for car insurance
        return car.getValue() * 0.03;
    }
}

// Main demonstration
public class Visitor {
    public static void main(String[] args) {
        Asset[] assets = {new House(500000), new Car(50000)};

        // Tax Calculation
        TaxVisitor taxVisitor = new TaxCalculationVisitor();
        double totalTax = 0;
        for (Asset asset : assets) {
            asset.accept(taxVisitor);
            totalTax += taxVisitor.visit((House) asset);
        }
        System.out.println("Total Tax: $" + totalTax);


        // Insurance Valuation (without changing asset classes!)
        TaxVisitor insuranceVisitor = new InsuranceVisitor();
        double totalInsurance = 0;
        for (Asset asset : assets) {
            asset.accept(insuranceVisitor);
            totalInsurance += insuranceVisitor.visit((House) asset);
        }
        System.out.println("Total Insurance: $" + totalInsurance);
    }
}
