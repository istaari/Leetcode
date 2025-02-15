- **1. Deterministic Nature**

  A **Deterministic Finite State Machine (DFSM)** means that **given a specific state and an input (decision), the next state is uniquely determined**. Let's analyze this:

    - If you **own a stock (`Hold`)**, you have two choices:
        - Do nothing → Stay in `Hold`.
        - Sell → Move to `Sold`.

    - If you **just sold a stock (`Sold`)**, there is **only one possible transition**:
        - Move to `Rest` (cooldown applies).

    - If you are **not holding a stock and not in cooldown (`Rest`)**, you also have two choices:
        - Do nothing → Stay in `Rest`.
        - Buy → Move to `Hold`.

- **2. Difference Between DFSM and NDFSM**

  A **Non-Deterministic FSM (NDFSM)** allows **multiple possible next states for the same input, without a clear rule**. That is, for the same state and input, different transitions could happen at the same time.

    - In this problem, at any given moment, **the transitions follow strict rules**.
    - Given a state and a choice, you always move to **one specific next state** (no parallel paths).

    - The key difference is **in an NDFSM, the machine can "magically" pick different paths without clear rules**.
    - Here, once a choice is made, the transition is **fixed**, which makes it **deterministic**.
