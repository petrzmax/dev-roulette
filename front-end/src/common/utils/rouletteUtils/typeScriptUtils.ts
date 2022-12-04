export function isEmpty(array: any[]): boolean {
  return !(Array.isArray(array) && array.length);
}

export function repeatArray<T>(array: readonly T[], n: number): T[] {
  return Array(n).fill(array).flat();
}
