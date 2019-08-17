package test;

public class ArrayDemo
{
    public static void main(String[] args)
    {
        // Simple 'scalar' variables hold one value
        int number = 2;
        String name = "Fred";       
        System.out.println(name + " has number " + number);

        // 'Array[]' variables hold several values
        // Similar to using 'new NameOfClass()' to create one instance of
        // a class object, you need to use 'new name_of_thing[count]'
        // to allocate room for the array elements
        int[] numbers = new int[3];
        // Then you can assign values to the elements
        numbers[0] = 2;
        numbers[1] = 42;
        numbers[2] = 65;

        // The 'new ..[]' to allocate room for the array elements
        // and the assignment of initial values can be combined like this.
        // No need to say 'new String[3]' since the count is implied by
        // the number of initial elements.
        String[] names = new String[]
        {
            "Fred",
            "Jane",
            "Isigorius"
        };

        // NameOfArray.length gives the, well, size of the array
        for (int i=0; i<names.length; ++i)
            System.out.println(names[i] + " has number " + numbers[i]);

        // When dealing with only one array and you don't care
        // about the index of the current element, you simply
        // want to iterate over all elements, you can do that like this:
        for (String one_name : names)
            System.out.println("One of the names is " + one_name);
    }
}