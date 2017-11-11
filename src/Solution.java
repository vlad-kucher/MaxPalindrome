import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        //получаем все простые числа меньше 100_000
        List<Integer> primes = getPrimes();

        //удаляем простые числа в которых меньше 5 цифр
        primes.removeIf(p -> p < 10000);

        //ищем максимальный палиндом для каждого простого числа
        long[] maxPalindromesForEachPrime = getMaxPalindromesForEachPrime(primes);

        //выводим результат
        int index = getIndexOfMaxPalindrome(maxPalindromesForEachPrime);
        System.out.println("Max palindrome: " + maxPalindromesForEachPrime[index] + " = " + primes.get(index) +
                " * " + maxPalindromesForEachPrime[index]/primes.get(index));
    }

    private static List<Integer> getPrimes(){
        List<Integer> primes = new ArrayList<>();

        label: for (int i = 2; i < 100000; i++) {
            for (int p : primes) {
                if (i%p == 0) {
                    continue label;
                }
            }
            primes.add(i);
        }

        return primes;
    }

    private static long[] getMaxPalindromesForEachPrime(List<Integer> primes) {
        long[] palindromes = new long[primes.size()];

        label: for (int i = primes.size()-1; i >= 0 ; i--) {
            for (int j = i-1; j >= 0 ; j--) {
                long n = (long) primes.get(i) * primes.get(j);
                if (checkPalindrome(n)) {
                    palindromes[i] = n;
                    continue label;
                }
            }
        }

        return palindromes;
    }

    private static int getIndexOfMaxPalindrome(long[] palindromes) {
        int maxIndex = 0;
        long maxElem = 0;

        for (int i = 0; i < palindromes.length; i++) {
            if (palindromes[i]>maxElem) {
                maxElem = palindromes[i];
                maxIndex = i;
            }
        }

        if (maxElem == 0) {
            System.out.println("No palindromes found");
            System.exit(0);
        }

        return maxIndex;
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
