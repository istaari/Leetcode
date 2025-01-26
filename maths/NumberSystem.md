
## Number System conversion

### 1. **Convert from Binary to Decimal, Octal, and Hexadecimal**

####  Binary to Decimal

- To convert binary to decimal, multiply each bit by 2 raised to the power of its position, starting
  from 0 on the right.

  ```
  // Convert binary `1011` to decimal.

  1011 (binary) = 1*2^3 + 0*2^2 + 1*2^1 + 1*2^0
               = 8 + 0 + 2 + 1
               = 11 (decimal)
  ```

#### Binary to Octal

-  Group the binary digits into sets of 3 bits from the right, then convert each group into its octal
  equivalent.

    ```
    // Convert binary `101110` to octal.

    Grouping: 101 110
    101 (binary) = 5 (octal)
    110 (binary) = 6 (octal)
    
    So, 101110 (binary) = 56 (octal)
    ```

#### Binary to Hexadecimal

- Group the binary digits into sets of 4 bits from the right, then convert each group into its
  hexadecimal equivalent.

  ```
  // Convert binary `10111101` to hexadecimal.

  Grouping: 1011 1101
  1011 (binary) = B (hex)
  1101 (binary) = D (hex)
  
  So, 10111101 (binary) = BD (hex)
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

### 3. **Convert from Octal to Binary, Decimal, and Hexadecimal**

#### Octal to Binary

- Convert each octal digit into its 3-bit binary equivalent.

  ```
  // Convert octal `75` to binary

  7 (octal) = 111 (binary)
  5 (octal) = 101 (binary)
  
  So, 75 (octal) = 111101 (binary)
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

#### Octal to Hexadecimal

- First convert the octal number to binary, then group the binary digits into sets of 4 to convert to
  hexadecimal.

  ```
  // Convert octal `27` to hexadecimal.

  2 (octal) = 010 (binary)
  7 (octal) = 111 (binary)
  So, 27 (octal) = 010 111 (binary)
  
  Grouping as 4 bits: 0010 1111
  0010 (binary) = 2 (hex)
  1111 (binary) = F (hex)
  
  So, 27 (octal) = 2F (hex)
  ```

### 4. **Convert from Hexadecimal to Binary, Decimal, and Octal**

####  Hexadecimal to Binary

- Convert each hexadecimal digit to its 4-bit binary equivalent.

  ```
  // Convert hexadecimal `2A7` to binary.

  2 (hex) = 0010 (binary)
  A (hex) = 1010 (binary)
  7 (hex) = 0111 (binary)
  
  So, 2A7 (hex) = 001010100111 (binary)
  ```

####  Hexadecimal to Decimal

- Multiply each hex digit by 16 raised to the power of its position (starting from 0 from the right).

  ```
  // Convert hexadecimal 3F to decimal.

  3F (hex) = 3*16^1 + 15*16^0
           = 3*16 + 15*1
           = 48 + 15
           = 63 (decimal)
  ```

#### Hexadecimal to Octal

- First convert hexadecimal to binary, then group binary digits in sets of 3 to convert to octal.

  ```
  // Convert hexadecimal 4B to octal.

  4 (hex) = 0100 (binary)
  B (hex) = 1011 (binary)
  
  So, 4B (hex) = 0100 1011 (binary)
  
  Group into 3 bits: 000 100 101 011
  000 (binary) = 0 (octal)
  100 (binary) = 4 (octal)
  101 (binary) = 5 (octal)
  011 (binary) = 3 (octal)
  
  So, 4B (hex) = 0453 (octal)
  ```