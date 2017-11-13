import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        List<Integer> primes = getFiveDigitPrimes();
        findMaxPalindrome(primes);
    }

    private static List<Integer> getFiveDigitPrimes(){
        List<Integer> primes = new ArrayList<>();

        outer: for (int i = 2; i < 100000; i++) {
            for (int prime : primes) {
                if (i%prime == 0) {
                    continue outer;
                }
            }
            primes.add(i);
        }

        primes.removeIf(prime -> prime < 10000);
        return primes;
    }

    private static void findMaxPalindrome(List<Integer> primes) {
        long maxPalindrome = 0;
        int multiplier = 0;

        outer: for (int i = primes.size()-1; i >= 0 ; i--) {
            for (int j = i-1; j >= 0 ; j--) {
                long n = (long) primes.get(i) * primes.get(j);
                if (checkPalindrome(n)) {
                    if (maxPalindrome < n) {
                        maxPalindrome = n;
                        multiplier = primes.get(i);
                    }
                    continue outer;
                }
            }
        }

        if (maxPalindrome == 0) {
            System.out.println("No palindromes found");
        } else {
            System.out.format("Max palindrome: %d = %d * %d", maxPalindrome, multiplier, maxPalindrome/multiplier);
        }
    }

    private static boolean checkPalindrome(long number) {
        long reverse = 0;
        long n = number;

        while (n>0) {
            reverse = reverse*10 + n%10;
            n /= 10;
        }

        return number == reverse;
    }
}
