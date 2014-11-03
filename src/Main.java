import java.math.BigInteger;



public class Main {
	public static void main(String[] args) {
		for (int i = 0; i < 94; i++) {
			BigInteger num = BigInteger.valueOf(i);
			System.out.println(fib(num));
		}
	}

	private static BigInteger fib(BigInteger n) {
		BigInteger p = new BigInteger("a94fad42221f27ad", 16);
		BigInteger v = new BigInteger("0b92025517515f58", 16);
		BigInteger v_inv = new BigInteger("242d231e3eb01b01", 16);
		BigInteger a = powmod(BigInteger.ONE.add(v), n, p);
		BigInteger b = powmod(p.add(BigInteger.ONE).subtract(v), n, p);
		BigInteger pow2_inv = powmod(BigInteger.valueOf(2), p.subtract(BigInteger.ONE).subtract(n), p);
		BigInteger diff = submod(a, b, p);
		BigInteger factor = mulmod(v_inv, pow2_inv, p);
		return mulmod(diff, factor, p);
	}

	private static BigInteger mulmod(BigInteger a, BigInteger b, BigInteger p) {
		BigInteger r = BigInteger.ZERO;
		while (b.compareTo(BigInteger.ZERO) > 0) {
			if (!b.and(BigInteger.ONE).equals(BigInteger.ZERO)) {
				r = addmod(r, a, p);
			}
			b = b.shiftRight(1);
			a = addmod(a, a, p);
		}
		return r;
	}

	private static BigInteger addmod(BigInteger a, BigInteger b, BigInteger p) {
		if (p.subtract(b).compareTo(a) > 0) {
			return a.add(b);
		} else {
			return a.add(b).subtract(p);
		}
	}

	private static BigInteger submod(BigInteger a, BigInteger b, BigInteger p) {
		if (a.compareTo(b) >= 0) {
			return a.subtract(b);
		} else {
			return p.subtract(b).add(a);
		}
	}

	private static BigInteger powmod(BigInteger a, BigInteger e, BigInteger p) {
		BigInteger r = BigInteger.ONE;
		while (e.compareTo(BigInteger.ZERO) > 0) {
			if (!e.and(BigInteger.ONE).equals(BigInteger.ZERO)) {
				r = mulmod(r, a, p);
			}
			e = e.shiftRight(1);
			a = mulmod(a, a, p);
		}
		return r;
	}
}
