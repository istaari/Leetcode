### **1. Linear Equations (Degree = 1)**

A **linear equation** is an equation where the highest power of the variable is **1**. It represents a straight line
when graphed.

<h4>General Form:</h4>

<p><code>ax + b = 0</code></p>

<p>where:</p>
<ul>
  <li><code>a, b</code> are constants</li>
  <li><code>x</code> is the variable</li>
  <li><code>a != 0</code></li>
</ul>

<h4>Examples:<h4>

<ul>
  <li><code>3x + 5 = 0</code></li>
  <li><code>2y - 7 = 3</code></li>
  <li><code>x + y = 10</code> (a linear equation in two variables)</li>
</ul>


**Graph:** A straight line.

### **2. Quadratic Equations (Degree = 2)**

A **quadratic equation** is a polynomial equation where the highest power of the variable is **2**. It represents a *
*parabola** when graphed.

<h4>General Form:<h4>

<p><code>ax<sup>2</sup> + bx + c = 0</code></p>

<p>where:</p>
<ul>
  <li><code>a, b, c</code> are constants</li>
  <li><code>a != 0</code></li>
</ul>

<h4>Examples:<h4>

<ul>
  <li><code>x<sup>2</sup> - 4x + 3 = 0</code></li>
  <li><code>2y<sup>2</sup> + 5y - 7 = 0</code></li>
  <li><code>t<sup>2</sup> - 9 = 0</code></li>
</ul>


### **Solution Methods:**

- **Factoring** (if possible)
- **Quadratic Formula**:  
  - x = (-b ± √(b² - 4ac)) / 2a
- **Completing the square**
- **Graphing**

**Graph:** A **parabola** (U-shaped curve).

---

### **3. Cubic Equations (Degree = 3)**

A **cubic equation** is a polynomial equation where the highest power of the variable is **3**. It represents a curve
that may change direction twice.

<h4>General Form:<h4>

<p><code>ax<sup>3</sup> + bx<sup>2</sup> + cx + d = 0</code></p>

<p>where:</p>
<ul>
  <li><code>a, b, c, d</code> are constants</li>
  <li><code>a != 0</code></li>
</ul>

<h4>Examples:<h4>

<ul>
  <li><code>x<sup>3</sup> - 6x<sup>2</sup> + 11x - 6 = 0</code></li>
  <li><code>2y<sup>3</sup> + 3y<sup>2</sup> - 5y + 1 = 0</code></li>
  <li><code>t<sup>3</sup> - 4t = 0</code></li>
</ul>


### **Solution Methods:**

- **Factoring** (if possible)
- **Synthetic division**
- **Graphing**
- **Cardano’s formula** (for general cubic equations)

**Graph:** A curve that may have one or two turning points.

---

### **4. Polynomial Equations (Degree = n)**

A **polynomial equation** is a general equation where the highest power of the variable is **n** (which can be any whole
number greater than or equal to 1).

<h4>General Form:<h4>

<p><code>a<sub>n</sub>x<sup>n</sup> + a<sub>n-1</sub>x<sup>n-1</sup> + ... + a<sub>1</sub>x + a<sub>0</sub> = 0</code></p>

<p>where:</p>
<ul>
  <li><code>a<sub>n</sub>, a<sub>n-1</sub>, ..., a<sub>1</sub>, a<sub>0</sub></code> are constants</li>
  <li><code>a<sub>n</sub> != 0</code></li>
  <li><code>n</code> is a positive integer (degree of the polynomial)</li>
</ul>

<h4>Examples:<h4>

<ul>
  <li><code>x<sup>4</sup> - 3x<sup>2</sup> + 2 = 0</code> (Quartic, Degree = 4)</li>
  <li><code>x<sup>5</sup> - 4x + 3 = 0</code> (Quintic, Degree = 5)</li>
  <li><code>3x<sup>6</sup> - 2x<sup>4</sup> + 5x<sup>2</sup> - x + 7 = 0</code> (General Polynomial)</li>
</ul>


### **Solution Methods:**

- **Factoring** (if possible)
- **Numerical approximation methods** (like Newton’s method)
- **Graphing**

**Graph:** Depends on the degree. Higher-degree polynomials have more complex shapes.

<h3>Table</h3>

| Type of Equation | General Form                                                                       | Degree         | Graph Shape    |
|------------------|------------------------------------------------------------------------------------|----------------|----------------|
| Linear           | <code>ax + b = 0</code>                                                            | 1              | Straight line  |
| Quadratic        | <code>ax<sup>2</sup> + bx + c = 0</code>                                           | 2              | Parabola       |
| Cubic            | <code>ax<sup>3</sup> + bx<sup>2</sup> + cx + d = 0</code>                          | 3              | S-curve        |
| Polynomial       | <code>a<sub>n</sub>x<sup>n</sup> + ... + a<sub>1</sub>x + a<sub>0</sub> = 0</code> | <code>n</code> | Complex curves |




---


## 🧠 The Ultimate Math Roadmap for Deep Learning

This roadmap is designed for beginners and focuses on **intuition over rigorous proofs**. The goal is to understand *how* and *why* the math works in the context of building and training neural networks.

---

### ## 🏫 **Phase 1: The Foundations — Functions & Equations**

This is the bedrock. We need to be comfortable with how inputs relate to outputs and the difference between simple and complex relationships.

* **Key Concepts:**
    * **Linear Equations ($y = mx + b$):** Understand slope and intercept. This is the fundamental building block of a neuron's calculation before activation.
    * **Non-Linear Functions:** Get a feel for the shapes of functions like $y = x^2$, $e^x$, and $\log(x)$.
    * **Function Composition ($f(g(x))$):** Understand how to chain functions together.

* **Why it Matters in Deep Learning:**
    * A neural network is essentially a very deep **composite function**, like $f_3(f_2(f_1(x)))$. Each layer is a function.
    * The "magic" of deep learning comes from alternating simple **linear** operations with **non-linear "activation" functions**. Without non-linearity, a deep network would just be equivalent to a single, simple linear model.

---

### ## 🔤 **Phase 2: Linear Algebra — The Language of Data**

Linear algebra gives us the tools to perform computations on large amounts of data efficiently. It's the language we use to structure and manipulate the inputs, outputs, and parameters of a network.

* **Key Concepts:**
    * **Vectors:** A list of numbers. Can represent a single data point (e.g., an image flattened into a list of pixel values) or a neuron's weights.
    * **Matrices:** A grid of numbers. Can represent an entire dataset (rows=samples, columns=features) or the weights of an entire layer in the network.
    * **Dot Product:** The fundamental computation between an input vector and a weight vector.
    * **Matrix Multiplication:** The operation that processes an entire batch of data through a layer of neurons at once. **This is the workhorse of deep learning.**
    * **Tensors:** The general term for these multi-dimensional arrays. A vector is a 1D tensor, and a matrix is a 2D tensor. You'll see this term everywhere in frameworks like TensorFlow and PyTorch. 

* **Why it Matters in Deep Learning:**
    * Instead of writing thousands of individual equations, we can express the entire forward pass of a network layer in one clean equation: $\text{activations} = \text{activation_function}(W \cdot X + b)$, where $W$ is a matrix, and $X$ and $b$ are vectors (or matrices for a batch of data).

---

### ## 📊 **Phase 3: Probability & Statistics — The Science of Uncertainty**

Machine learning is all about making predictions and quantifying uncertainty. Probability and statistics provide the framework for this. This isn't optional; it's central to understanding what "learning" means.

* **Key Concepts:**
    * **Basic Probability:** Understanding likelihood and events.
    * **Probability Distributions:** Describing the likelihood of different outcomes (e.g., Normal distribution). A model's goal is often to learn the underlying probability distribution of the data.
    * **Mean, Variance, Standard Deviation:** Core metrics to describe data.
    * **Likelihood:** How well a model's parameters explain the observed data. We train models to **maximize the likelihood** of the training data.
    * **Cross-Entropy:** A key concept derived from information theory that measures the difference between two probability distributions (e.g., the model's predictions and the true labels). This is the most common **loss function** for classification tasks.

* **Why it Matters in Deep Learning:**
    * **Loss functions** are built on statistical principles. We don't just want the model to be "right"; we want it to be confidently right and to quantify how "wrong" it is.
    * The **Softmax** activation function, used in the final layer of classification models, outputs a probability distribution.

---

### ## ⚙️ **Phase 4: Calculus — The Engine of Optimization**

Calculus tells us how to make changes. If our network is wrong, calculus gives us a precise way to figure out *which* parameters to change and by *how much* to make it less wrong.

* **Key Concepts:**
    * **Derivatives:** The instantaneous rate of change, or the **slope** of a function. It tells us how a function's output changes if we make a tiny nudge to its input.
    * **Gradients:** A vector of partial derivatives. For a function with many inputs (like a loss function that depends on millions of weights), the gradient points in the direction of the **steepest increase** of that function.
    * **The Chain Rule:** The most critical concept here. It allows us to calculate the derivative of a complex, composite function (like a neural network!) by breaking it down and finding the derivative of each smaller function in the chain.

* **Why it Matters in Deep Learning:**
    * **Training is optimization.** We want to find the weights that **minimize the loss function**.
    * The **gradient** of the loss function tells us how to adjust the weights. To minimize the loss, we "nudge" the weights in the **opposite direction of the gradient**. This famous algorithm is called **Gradient Descent**. 
    * **Backpropagation** is just a clever and efficient algorithm for applying the chain rule to calculate the gradients for all weights in the network.

---

### ## 🏆 **Phase 5: The Synthesis — Backpropagation in Action**

This is where everything comes together. You don't need to learn new math here; you just need to see how the previous phases combine to create the learning algorithm.

1.  **Forward Pass:** We feed data into the network. At each layer, we perform **matrix multiplication** (Linear Algebra) and apply an activation **function** (Foundations).
2.  **Loss Calculation:** We compare the network's final prediction to the true label using a loss function like Cross-Entropy (**Probability & Statistics**). This gives us a single number representing the model's error.
3.  **Backward Pass (Backpropagation):** We compute the **gradient** of the loss with respect to every single weight in the network. This is done by applying the **Chain Rule** (**Calculus**) layer by layer, starting from the end and moving backward.
4.  **Weight Update:** We adjust each weight by taking a small step in the opposite direction of its gradient ($\text{new_weight} = \text{old_weight} - \text{learning_rate} \times \text{gradient}$). This is **Gradient Descent** in action.

Repeat these four steps thousands of times, and the network learns!

