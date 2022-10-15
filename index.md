# Week 3 Lab Report

## Part 1
* This is the code for my **Simplest Search Engine fom week 2**:

```
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> lst1 = new ArrayList<>();

    public String handleRequest(URI url) {
        
        if (url.getPath().equals("/")) {
            return String.format("List of String: %s", lst1);
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    lst1.add(parameters[1]);
                    return String.format("List of Strings added %s! It's now %s", parameters[1], lst1);
                }
            }
            else if(url.getPath().contains("/search")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    ArrayList<String> ex = new ArrayList<>();
                    for (int i = 0; i < lst1.size(); i++) {
                        if (lst1.get(i).contains(parameters[1])) {
                            ex.add(lst1.get(i));
                        }
                    }
                    return String.format("Search words: %s", ex);
                
                }
            }
            
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
```

* This is the result after I using two add and add two values "apple" and "banana". The method handleRequest in my code called. The values of the relevant arguments to those methods are URI url or "/add?s=apple" and "/add?s=banana". The values of any relevant fields of the class are ArrayList<String> lst1 or [apple, banana]. If those values change, they change the array lst1 by adding strings into the array by the time the request is done processing.

![](https://github.com/tnduong2807/wavelet/blob/master/Screenshot%20(38).png?raw=true)

* This is the result when I add the value "pineapple" to the already existing array. The method handleRequest in my code called. The values of the relevant arguments to the method is URI url or "/add?s=pineapple". The values of any relevant field of the class are ArrayList<String> lst1 or [apple, banana, pineapple]. If those values change, they change array lst1 by adding strings into the array by the time the request is done processing.

![](https://github.com/tnduong2807/wavelet/blob/master/Screenshot%20(39).png?raw=true)


* This is the result when using query search to find the elements in the array that contains the input string. The method handleRequest in my code called. The values of the relevant arguments to the method is URI url or "/search?s=app". The values of any relevant fileds of the class are [apple, pineapple] print out using String.format function. If those values change, they don't change the array lst1 but the values got add to another array ex and got print out by function String.format.

![](https://github.com/tnduong2807/wavelet/blob/master/Screenshot%20(40).png?raw=true)


## Part 2
* **First bug in the code from file ArrayExamples.java:**

```
static void reverseInPlace(int[] arr) {
    for(int i = 0; i < arr.length; i += 1) {
      arr[i] = arr[arr.length - i - 1];
    }
  }
```

* The failure-inducing input is testing the array list {1, 2, 3, 4, 5}, the array list have to reverse and change into {5, 4, 3, 2, 1} after went in the function reverseInPlace.
* The symptom is the array list change into {5, 4, 3, 4, 5}.
* The bug is that the array change and override the elements in it for the second half of the array.
* The connection between the symptom and the bug is that the bug is the problem or logic error in the function and the symptom is the output of the function. The bug cause that particular symptom for that particular input because the function have an logic error, it change the function and override the elements inside the array list.
* This is the code after fixed:

```
static void reverseInPlace(int[] arr) {
    int[] newArray = arr.clone();
    for(int i = 0; i < arr.length; i += 1) {
      arr[i] = newArray[(arr.length - i) - 1];
    }
  }
```

* **Second bug in the code from file ListExamples.java:**

```
static List<String> merge(List<String> list1, List<String> list2) {
    List<String> result = new ArrayList<>();
    int index1 = 0, index2 = 0;
    while(index1 < list1.size() && index2 < list2.size()) {
      if(list1.get(index1).compareTo(list2.get(index2)) < 0) {
        result.add(list1.get(index1));
        index1 += 1;
      }
      else {
        result.add(list2.get(index2));
        index2 += 1;
      }
    }
    while(index1 < list1.size()) {
      result.add(list1.get(index1));
      index1 += 1;
    }
    while(index2 < list2.size()) {
      result.add(list2.get(index2));
      index1 += 1;
    }
    return result;
  }
```

* The failure-inducing is testing the two array list {a, c, e} and {b, d}, the function should return an output array list that have elements {a, b, c, d, e}.
* The symptom is that the program trow an exception say OutOfMemoryError: Java heap space.
* The bug is that the while loops keep running, don't stop and cause the program to crash.
* The connection between the symptom and the bug is that the bug is while loops keep running and cause the symptom that the program throw an exception. The bug cause that particular symptom for that particular input because the function have an run time exception, it cause by the conditions of the while loops never statify so it don't end cause the program to crash.
* This is the code after fixed:

```
static List<String> merge(List<String> list1, List<String> list2) {
    List<String> result = new ArrayList<>();
    int index1 = 0, index2 = 0;
    while(index1 < list1.size() || index2 < list2.size()) {
      if (index1 < list1.size() && index2 < list2.size()) {
        if(list1.get(index1).compareTo(list2.get(index2)) < 0) {
          result.add(list1.get(index1));
          index1 += 1;
        }
        else {
          result.add(list2.get(index2));
          index2 += 1;
        }
      }
      else if(index1 < list1.size()) {
        result.add(list1.get(index1));
          index1 += 1;
      }
      else {
        result.add(list2.get(index2));
          index2 += 1;
      }
    }
    return result;
  }
```
