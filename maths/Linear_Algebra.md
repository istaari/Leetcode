### **1. Linear Equations (Degree = 1)**

A **linear equation** is an equation where the highest power of the variable is **1**. It represents a straight line when graphed.

* **General Form:** $ax + b = 0$
* **Where:** $a, b$ are constants, $x$ is the variable, and $a \neq 0$.

**Core Principle: Isolation**
The goal is to manipulate the equation so that the variable ($x$) stands alone on one side.
* **The Golden Rule of Algebra:** Whatever operation you perform on one side of the equal sign, you **must** perform on the other side.
* **Inverse Operations:** Cancel values using opposites (Addition $\leftrightarrow$ Subtraction, Multiplication $\leftrightarrow$ Division).

**Examples:**
* $3x + 5 = 0$
* $2y - 7 = 3$
* $x + y = 10$



[Image of graph of linear equation y=mx+c]


---

### **2. Quadratic Equations (Degree = 2)**

A **quadratic equation** is a polynomial equation where the highest power of the variable is **2**. It represents a **parabola** (U-shape) when graphed.

* **General Form:** $ax^2 + bx + c = 0$
* **Where:** $a, b, c$ are constants and $a \neq 0$.

**Core Principle: Zero Product Property**
You generally cannot "isolate" $x$ directly. Instead, you set the equation to zero.
* **Zero Product Property:** If $a \cdot b = 0$, then either $a = 0$ or $b = 0$.
* **The Discriminant ($\Delta = b^2 - 4ac$):** Determines the solution type:
    * $\Delta > 0$: Two real roots.
    * $\Delta = 0$: One repeated root.
    * $\Delta < 0$: Two complex roots.

**Solution Methods:**
1.  **Factoring** (if possible)
2.  **Quadratic Formula:** $x = \frac{-b \pm \sqrt{b^2 - 4ac}}{2a}$
3.  **Completing the square**
4.  **Graphing**

**Examples:**
* $x^2 - 4x + 3 = 0$
* $t^2 - 9 = 0$



[Image of parabola graph of quadratic equation]


---

### **3. Cubic Equations (Degree = 3)**

A **cubic equation** is a polynomial equation where the highest power of the variable is **3**. It represents a curve that may change direction twice (S-curve).

* **General Form:** $ax^3 + bx^2 + cx + d = 0$
* **Where:** $a, b, c, d$ are constants and $a \neq 0$.

**Core Principle: Depressing the Polynomial**
The strategy is often to find *one* root, divide it out, and reduce the remaining equation to a Quadratic.
* **Factoring by Grouping:** Split four terms into two pairs to find common binomial factors.
* **Sum/Difference of Cubes:**
    * $a^3 \pm b^3 = (a \pm b)(a^2 \mp ab + b^2)$
* **Synthetic Division:** A shorthand division method used to test roots and break the cubic down.

**Solution Methods:**
1.  **Factoring** (e.g., by grouping)
2.  **Synthetic division**
3.  **Graphing**
4.  **Cardano’s formula** (for general cases)

**Examples:**
* $x^3 - 6x^2 + 11x - 6 = 0$
* $t^3 - 4t = 0$



[Image of cubic function graph]


---

### **4. Polynomial Equations (Degree = n)**

A **polynomial equation** is a general equation where the highest power of the variable is **n**.

* **General Form:** $a_nx^n + a_{n-1}x^{n-1} + ... + a_1x + a_0 = 0$
* **Where:** $n$ is a positive integer (the degree).

**Core Principle: Root Behavior Theorems**
For degrees $n > 2$, we rely on theorems to find roots rather than simple calculation steps.
* **Fundamental Theorem of Algebra:** A polynomial of degree $n$ has exactly $n$ roots (counting complex/repeated ones).
* **Rational Root Theorem:** Possible rational roots = $\pm \frac{\text{Factors of Constant Term}}{\text{Factors of Leading Coefficient}}$.
* **Factor Theorem:** If $P(c) = 0$, then $(x - c)$ is a factor.

**Solution Methods:**
1.  **Factoring**
2.  **Numerical approximation** (e.g., Newton’s method)
3.  **Graphing**

**Examples:**
* $x^4 - 3x^2 + 2 = 0$ (Quartic)
* $x^5 - 4x + 3 = 0$ (Quintic)

---

### **Summary Table**

| Type of Equation | General Form | Degree | Graph Shape | Key Solving Principle |
| :--- | :--- | :--- | :--- | :--- |
| **Linear** | $ax + b = 0$ | 1 | Straight line | Isolation & Inverse Ops |
| **Quadratic** | $ax^2 + bx + c = 0$ | 2 | Parabola | Zero Product Property |
| **Cubic** | $ax^3 + bx^2 + cx + d = 0$ | 3 | S-curve | Depressing the Polynomial |
| **Polynomial** | $a_nx^n + ... + a_0 = 0$ | $n$ | Complex curves | Rational Root Theorem |