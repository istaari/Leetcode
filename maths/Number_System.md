## Number System conversion

### 1. **Convert from Binary, Octal, Hexadecimal to Decimal**

#### Binary to Decimal

- To convert binary to decimal, multiply each bit by 2 raised to the power of its position, starting
  from 0 on the right.

  ```
  // Convert binary `1011` to decimal.

  1011 (binary) = 1*2^3 + 0*2^2 + 1*2^1 + 1*2^0
               = 8 + 0 + 2 + 1
               = 11 (decimal)
  ```

#### Octal to Decimal

- Multiply each digit by 8 raised to the power of its position from the right (starting from 0).

  ```
  // Convert octal `342` to decimal.

  342 (octal) = 3*8^2 + 4*8^1 + 2*8^0
              = 3*64 + 4*8 + 2*1
              = 192 + 32 + 2
              = 226 (decimal)
  ```

#### Hexadecimal to Decimal

- Multiply each hex digit by 16 raised to the power of its position (starting from 0 from the right).

  ```
  // Convert hexadecimal 3F to decimal.

  3F (hex) = 3*16^1 + 15*16^0
           = 3*16 + 15*1
           = 48 + 15
           = 63 (decimal)
  ```

### 2. **Convert from Decimal to Binary, Octal, and Hexadecimal**

#### Decimal to Binary

- Divide the decimal number by 2, record the remainder, and repeat until the quotient is 0. The binary
  result is the remainders read from bottom to top.

  ```
  // Convert decimal `23` to binary.

  23 ÷ 2 = 11 remainder 1
  11 ÷ 2 = 5 remainder 1
  5 ÷ 2 = 2 remainder 1
  2 ÷ 2 = 1 remainder 0
  1 ÷ 2 = 0 remainder 1

  So, 23 (decimal) = 10111 (binary)
  ```

#### Decimal to Octal

- Divide the decimal number by 8, record the remainder, and repeat until the quotient is 0.

  ```
  // Convert decimal `83` to octal.

  83 ÷ 8 = 10 remainder 3
  10 ÷ 8 = 1 remainder 2
  1 ÷ 8 = 0 remainder 1
  
  So, 83 (decimal) = 123 (octal)
  ```

#### Decimal to Hexadecimal

- Divide the decimal number by 16, record the remainder, and repeat until the quotient is 0.

  ```
  // Convert decimal `255` to hexadecimal

  255 ÷ 16 = 15 remainder 15
  15 ÷ 16 = 0 remainder 15
  
  Since remainder 15 = F in hexadecimal,
  So, 255 (decimal) = FF (hex)
  ```


