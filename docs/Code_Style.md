# Google Java Style Guide

Table of Contents

1 Introduction
1.1 Terminology notes
1.2 Guide notes
2 Source file basics
2.1 File name
2.2 File encoding: UTF-8
2.3 Special characters
3 Source file structure
3.1 License or copyright information, if present
3.2 Package statement
3.3 Import statements
3.4 Class declaration
4 Formatting
4.1 Braces
4.2 Block indentation: +2 spaces
4.3 One statement per line
4.4 Column limit: 100
4.5 Line-wrapping
4.6 Whitespace
4.7 Grouping parentheses: recommended
4.8 Specific constructs
5 Naming
5.1 Rules common to all identifiers
5.2 Rules by identifier type
5.3 Camel case: defined
6 Programming Practices
6.1 @Override: always used
6.2 Caught exceptions: not ignored
6.3 Static members: qualified using class
6.4 Finalizers: not used
7 Javadoc
7.1 Formatting
7.2 The summary fragment
7.3 Where Javadoc is used

## 1 Introduction

This document serves as the complete definition of Google's coding standards for source code in the Java™ Programming Language. A Java source file is described as being in Google Style if and only if it adheres to the rules herein.

Like other programming style guides, the issues covered span not only aesthetic issues of formatting, but other types of conventions or coding standards as well. However, this document focuses primarily on the hard-and-fast rules that we follow universally, and avoids giving advice that isn't clearly enforceable (whether by human or tool).

### 1.1 Terminology notes

In this document, unless otherwise clarified:

The term class is used inclusively to mean an "ordinary" class, enum class, interface or annotation type (@interface).
The term member (of a class) is used inclusively to mean a nested class, field, method, or constructor; that is, all top-level contents of a class except initializers and comments.
The term comment always refers to implementation comments. We do not use the phrase "documentation comments", instead using the common term "Javadoc."
Other "terminology notes" will appear occasionally throughout the document.

### 1.2 Guide notes

Example code in this document is non-normative. That is, while the examples are in Google Style, they may not illustrate the only stylish way to represent the code. Optional formatting choices made in examples should not be enforced as rules.

## 2 Source file basics

### 2.1 File name

The source file name consists of the case-sensitive name of the top-level class it contains (of which there is exactly one), plus the .java extension.

### 2.2 File encoding: UTF-8

Source files are encoded in UTF-8.

### 2.3 Special characters

- 2.3.1 Whitespace characters

Aside from the line terminator sequence, the ASCII horizontal space character (0x20) is the only whitespace character that appears anywhere in a source file. This implies that:

All other whitespace characters in string and character literals are escaped.
Tab characters are not used for indentation.

- 2.3.2 Special escape sequences

For any character that has a special escape sequence (\b, \t, \n, \f, \r, \", \' and \\), that sequence is used rather than the corresponding octal (e.g. \012) or Unicode (e.g. \u000a) escape.

- 2.3.3 Non-ASCII characters

For the remaining non-ASCII characters, either the actual Unicode character (e.g. ∞) or the equivalent Unicode escape (e.g. \u221e) is used. The choice depends only on which makes the code easier to read and understand, although Unicode escapes outside string literals and comments are strongly discouraged.

**Tip**: In the Unicode escape case, and occasionally even when actual Unicode characters are used, an explanatory comment can be very helpful.

*Examples*:

Example	Discussion
String unitAbbrev = "μs";	Best: perfectly clear even without a comment.
String unitAbbrev = "\u03bcs"; // "μs"	Allowed, but there's no reason to do this.
String unitAbbrev = "\u03bcs"; // Greek letter mu, "s"	Allowed, but awkward and prone to mistakes.
String unitAbbrev = "\u03bcs";	Poor: the reader has no idea what this is.
return '\ufeff' + content; // byte order mark	Good: use escapes for non-printable characters, and comment if necessary.
Tip: Never make your code less readable simply out of fear that some programs might not handle non-ASCII characters properly. If that should happen, those programs are broken and they must be fixed.

3 Source file structure

A source file consists of, in order:

License or copyright information, if present
Package statement
Import statements
Exactly one top-level class
Exactly one blank line separates each section that is present.

3.1 License or copyright information, if present

If license or copyright information belongs in a file, it belongs here.

3.2 Package statement

The package statement is not line-wrapped. The column limit (Section 4.4, Column limit: 100) does not apply to package statements.

3.3 Import statements

3.3.1 No wildcard imports

Wildcard imports, static or otherwise, are not used.

3.3.2 No line-wrapping

Import statements are not line-wrapped. The column limit (Section 4.4, Column limit: 100) does not apply to import statements.

3.3.3 Ordering and spacing

Imports are ordered as follows:

All static imports in a single block.
All non-static imports in a single block.
If there are both static and non-static imports, a single blank line separates the two blocks. There are no other blank lines between import statements.

Within each block the imported names appear in ASCII sort order. (Note: this is not the same as the import statements being in ASCII sort order, since '.' sorts before ';'.)

3.3.4 No static import for classes

Static import is not used for static nested classes. They are imported with normal imports.

3.4 Class declaration

3.4.1 Exactly one top-level class declaration

Each top-level class resides in a source file of its own.

3.4.2 Ordering of class contents

The order you choose for the members and initializers of your class can have a great effect on learnability. However, there's no single correct recipe for how to do it; different classes may order their contents in different ways.

What is important is that each class uses some logical order, which its maintainer could explain if asked. For example, new methods are not just habitually added to the end of the class, as that would yield "chronological by date added" ordering, which is not a logical ordering.

3.4.2.1 Overloads: never split

When a class has multiple constructors, or multiple methods with the same name, these appear sequentially, with no other code in between (not even private members).

4 Formatting

Terminology Note: block-like construct refers to the body of a class, method or constructor. Note that, by Section 4.8.3.1 on array initializers, any array initializer may optionally be treated as if it were a block-like construct.

4.1 Braces

4.1.1 Braces are used where optional

Braces are used with if, else, for, do and while statements, even when the body is empty or contains only a single statement.

4.1.2 Nonempty blocks: K & R style

Braces follow the Kernighan and Ritchie style ("Egyptian brackets") for nonempty blocks and block-like constructs:

No line break before the opening brace.
Line break after the opening brace.
Line break before the closing brace.
Line break after the closing brace, only if that brace terminates a statement or terminates the body of a method, constructor, or named class. For example, there is no line break after the brace if it is followed by else or a comma.
Examples:

return () -> {
  while (condition()) {
    method();
  }
};

return new MyClass() {
  @Override public void method() {
    if (condition()) {
      try {
        something();
      } catch (ProblemException e) {
        recover();
      }
    } else if (otherCondition()) {
      somethingElse();
    } else {
      lastThing();
    }
  }
};
A few exceptions for enum classes are given in Section 4.8.1, Enum classes.

4.1.3 Empty blocks: may be concise

An empty block or block-like construct may be in K & R style (as described in Section 4.1.2). Alternatively, it may be closed immediately after it is opened, with no characters or line break in between ({}), unless it is part of a multi-block statement (one that directly contains multiple blocks: if/else or try/catch/finally).

Examples:

  // This is acceptable
  void doNothing() {}

  // This is equally acceptable
  void doNothingElse() {
  }
  // This is not acceptable: No concise empty blocks in a multi-block statement
  try {
    doSomething();
  } catch (Exception e) {}
4.2 Block indentation: +2 spaces

Each time a new block or block-like construct is opened, the indent increases by two spaces. When the block ends, the indent returns to the previous indent level. The indent level applies to both code and comments throughout the block. (See the example in Section 4.1.2, Nonempty blocks: K & R Style.)

4.3 One statement per line

Each statement is followed by a line break.

4.4 Column limit: 100

Java code has a column limit of 100 characters. A "character" means any Unicode code point. Except as noted below, any line that would exceed this limit must be line-wrapped, as explained in Section 4.5, Line-wrapping.

Each Unicode code point counts as one character, even if its display width is greater or less. For example, if using fullwidth characters, you may choose to wrap the line earlier than where this rule strictly requires.

Exceptions:

Lines where obeying the column limit is not possible (for example, a long URL in Javadoc, or a long JSNI method reference).
package and import statements (see Sections 3.2 Package statement and 3.3 Import statements).
Command lines in a comment that may be cut-and-pasted into a shell.
4.5 Line-wrapping

Terminology Note: When code that might otherwise legally occupy a single line is divided into multiple lines, this activity is called line-wrapping.

There is no comprehensive, deterministic formula showing exactly how to line-wrap in every situation. Very often there are several valid ways to line-wrap the same piece of code.

Note: While the typical reason for line-wrapping is to avoid overflowing the column limit, even code that would in fact fit within the column limit may be line-wrapped at the author's discretion.

Tip: Extracting a method or local variable may solve the problem without the need to line-wrap.

4.5.1 Where to break

The prime directive of line-wrapping is: prefer to break at a higher syntactic level. Also:

When a line is broken at a non-assignment operator the break comes before the symbol. (Note that this is not the same practice used in Google style for other languages, such as C++ and JavaScript.)
This also applies to the following "operator-like" symbols:
the dot separator (.)
the two colons of a method reference (::)
an ampersand in a type bound (<T extends Foo & Bar>)
a pipe in a catch block (catch (FooException | BarException e)).
When a line is broken at an assignment operator the break typically comes after the symbol, but either way is acceptable.
This also applies to the "assignment-operator-like" colon in an enhanced for ("foreach") statement.
A method or constructor name stays attached to the open parenthesis (() that follows it.
A comma (,) stays attached to the token that precedes it.
A line is never broken adjacent to the arrow in a lambda, except that a break may come immediately after the arrow if the body of the lambda consists of a single unbraced expression. Examples:
MyLambda<String, Long, Object> lambda =
    (String label, Long value, Object obj) -> {
        ...
    };

Predicate<String> predicate = str ->
    longExpressionInvolving(str);
Note: The primary goal for line wrapping is to have clear code, not necessarily code that fits in the smallest number of lines.

4.5.2 Indent continuation lines at least +4 spaces

When line-wrapping, each line after the first (each continuation line) is indented at least +4 from the original line.

When there are multiple continuation lines, indentation may be varied beyond +4 as desired. In general, two continuation lines use the same indentation level if and only if they begin with syntactically parallel elements.

Section 4.6.3 on Horizontal alignment addresses the discouraged practice of using a variable number of spaces to align certain tokens with previous lines.

4.6 Whitespace

4.6.1 Vertical Whitespace

A single blank line appears:

Between consecutive members or initializers of a class: fields, constructors, methods, nested classes, static initializers, and instance initializers.
Exception: A blank line between two consecutive fields (having no other code between them) is optional. Such blank lines are used as needed to create logical groupings of fields.
Exception: Blank lines between enum constants are covered in Section 4.8.1.
Between statements, as needed to organize the code into logical subsections.
Optionally before the first member or initializer, or after the last member or initializer of the class (neither encouraged nor discouraged).
As required by other sections of this document (such as Section 3, Source file structure, and Section 3.3, Import statements).
Multiple consecutive blank lines are permitted, but never required (or encouraged).

4.6.2 Horizontal whitespace

Beyond where required by the language or other style rules, and apart from literals, comments and Javadoc, a single ASCII space also appears in the following places only.

Separating any reserved word, such as if, for or catch, from an open parenthesis (() that follows it on that line
Separating any reserved word, such as else or catch, from a closing curly brace (}) that precedes it on that line
Before any open curly brace ({), with two exceptions:
@SomeAnnotation({a, b}) (no space is used)
String[][] x = {{"foo"}}; (no space is required between {{, by item 8 below)
Em ambos os lados de qualquer operador binário ou ternário. Isso também se aplica aos seguintes símbolos "como operador":
O e comercial de um tipo conjuntivo vinculado: <T extends Foo & Bar>
o tubo para um bloco catch que lida com múltiplas exceções: catch (FooException | BarException e)
o colon ( :) em uma fordeclaração aprimorada ("foreach")
A flecha em uma expressão lambda: (String str) -> str.length()
mas não
os dois dois pontos ( ::) de uma referência de método, que é escrita comoObject::toString
o separador de pontos ( .), que é escrito como object.toString()
Após ,:;ou os parênteses de fechamento ( )) de um elenco
Em ambos os lados da barra dupla ( //) que começa um comentário de fim de linha. Aqui, vários espaços são permitidos, mas não são necessários.
Entre o tipo e a variável de uma declaração: List<String> list
Opcional apenas dentro de ambas as chaves de um inicializador de matriz
new int[] {5, 6}e ambos são válidosnew int[] { 5, 6 }
Esta regra nunca é interpretada como exigindo ou proibindo espaço adicional no início ou no final de uma linha; aborda apenas o espaço interior .
4.6.3 Alinhamento horizontal: nunca necessário

Nota de terminologia: o alinhamento horizontal é a prática de adicionar um número variável de espaços adicionais no seu código com o objetivo de fazer certos tokens aparecerem diretamente abaixo de alguns outros tokens em linhas anteriores.

Esta prática é permitida, mas nunca é exigida pelo Google Style. Nem é necessário manter o alinhamento horizontal em locais onde já foi usado.

Here is an example without alignment, then using alignment:

private int x; // this is fine
private Color color; // this too

private int   x;      // permitted, but future edits
private Color color;  // may leave it unaligned
Tip: Alignment can aid readability, but it creates problems for future maintenance. Consider a future change that needs to touch just one line. This change may leave the formerly-pleasing formatting mangled, and that is allowed. More often it prompts the coder (perhaps you) to adjust whitespace on nearby lines as well, possibly triggering a cascading series of reformattings. That one-line change now has a "blast radius." This can at worst result in pointless busywork, but at best it still corrupts version history information, slows down reviewers and exacerbates merge conflicts.

4.7 Grouping parentheses: recommended

Optional grouping parentheses are omitted only when author and reviewer agree that there is no reasonable chance the code will be misinterpreted without them, nor would they have made the code easier to read. It is not reasonable to assume that every reader has the entire Java operator precedence table memorized.

4.8 Specific constructs

4.8.1 Enum classes

After each comma that follows an enum constant, a line break is optional. Additional blank lines (usually just one) are also allowed. This is one possibility:

private enum Answer {
  YES {
    @Override public String toString() {
      return "yes";
    }
  },

  NO,
  MAYBE
}
An enum class with no methods and no documentation on its constants may optionally be formatted as if it were an array initializer (see Section 4.8.3.1 on array initializers).

private enum Suit { CLUBS, HEARTS, SPADES, DIAMONDS }
Since enum classes are classes, all other rules for formatting classes apply.

4.8.2 Variable declarations

4.8.2.1 One variable per declaration

Every variable declaration (field or local) declares only one variable: declarations such as int a, b; are not used.

Exception: Multiple variable declarations are acceptable in the header of a for loop.

4.8.2.2 Declared when needed

Local variables are not habitually declared at the start of their containing block or block-like construct. Instead, local variables are declared close to the point they are first used (within reason), to minimize their scope. Local variable declarations typically have initializers, or are initialized immediately after declaration.

4.8.3 Arrays

4.8.3.1 Array initializers: can be "block-like"

Any array initializer may optionally be formatted as if it were a "block-like construct." For example, the following are all valid (not an exhaustive list):

new int[] {           new int[] {
  0, 1, 2, 3            0,
}                       1,
                        2,
new int[] {             3,
  0, 1,               }
  2, 3
}                     new int[]
                          {0, 1, 2, 3}
4.8.3.2 No C-style array declarations

The square brackets form a part of the type, not the variable: String[] args, not String args[].

4.8.4 Switch statements

Terminology Note: Inside the braces of a switch block are one or more statement groups. Each statement group consists of one or more switch labels (either case FOO: or default:), followed by one or more statements (or, for the last statement group, zero or more statements).

4.8.4.1 Indentation

As with any other block, the contents of a switch block are indented +2.

After a switch label, there is a line break, and the indentation level is increased +2, exactly as if a block were being opened. The following switch label returns to the previous indentation level, as if a block had been closed.

4.8.4.2 Fall-through: commented

Within a switch block, each statement group either terminates abruptly (with a break, continue, return or thrown exception), or is marked with a comment to indicate that execution will or might continue into the next statement group. Any comment that communicates the idea of fall-through is sufficient (typically // fall through). This special comment is not required in the last statement group of the switch block. Example:

switch (input) {
  case 1:
  case 2:
    prepareOneOrTwo();
    // fall through
  case 3:
    handleOneTwoOrThree();
    break;
  default:
    handleLargeNumber(input);
}
Notice that no comment is needed after case 1:, only at the end of the statement group.

4.8.4.3 The default case is present

Each switch statement includes a default statement group, even if it contains no code.

Exception: A switch statement for an enum type may omit the default statement group, if it includes explicit cases covering all possible values of that type. This enables IDEs or other static analysis tools to issue a warning if any cases were missed.

4.8.5 Annotations

Annotations applying to a class, method or constructor appear immediately after the documentation block, and each annotation is listed on a line of its own (that is, one annotation per line). These line breaks do not constitute line-wrapping (Section 4.5, Line-wrapping), so the indentation level is not increased. Example:

@Override
@Nullable
public String getNameIfPresent() { ... }
Exception: A single parameterless annotation may instead appear together with the first line of the signature, for example:

@Override public int hashCode() { ... }
Annotations applying to a field also appear immediately after the documentation block, but in this case, multiple annotations (possibly parameterized) may be listed on the same line; for example:

@Partial @Mock DataLoader loader;
There are no specific rules for formatting annotations on parameters, local variables, or types.

4.8.6 Comments

This section addresses implementation comments. Javadoc is addressed separately in Section 7, Javadoc.

Any line break may be preceded by arbitrary whitespace followed by an implementation comment. Such a comment renders the line non-blank.

4.8.6.1 Block comment style

Block comments are indented at the same level as the surrounding code. They may be in /* ... */ style or // ... style. For multi-line /* ... */ comments, subsequent lines must start with * aligned with the * on the previous line.

/*
 * This is          // And so           /* Or you can
 * okay.            // is this.          * even do this. */
 */
Comments are not enclosed in boxes drawn with asterisks or other characters.

Tip: When writing multi-line comments, use the /* ... */ style if you want automatic code formatters to re-wrap the lines when necessary (paragraph-style). Most formatters don't re-wrap lines in // ... style comment blocks.

4.8.7 Modifiers

Class and member modifiers, when present, appear in the order recommended by the Java Language Specification:

public protected private abstract default static final transient volatile synchronized native strictfp
4.8.8 Numeric Literals

long-valued integer literals use an uppercase L suffix, never lowercase (to avoid confusion with the digit 1). For example, 3000000000L rather than 3000000000l.

5 Naming

5.1 Rules common to all identifiers

Identifiers use only ASCII letters and digits, and, in a small number of cases noted below, underscores. Thus each valid identifier name is matched by the regular expression \w+ .

In Google Style special prefixes or suffixes, like those seen in the examples name_, mName, s_name and kName, are not used.

5.2 Rules by identifier type

5.2.1 Package names

Package names are all lowercase, with consecutive words simply concatenated together (no underscores). For example, com.example.deepspace, not com.example.deepSpace or com.example.deep_space.

5.2.2 Class names

Class names are written in UpperCamelCase.

Class names are typically nouns or noun phrases. For example, Character or ImmutableList. Interface names may also be nouns or noun phrases (for example, List), but may sometimes be adjectives or adjective phrases instead (for example, Readable).

There are no specific rules or even well-established conventions for naming annotation types.

Test classes are named starting with the name of the class they are testing, and ending with Test. For example, HashTest or HashIntegrationTest.

5.2.3 Method names

Method names are written in lowerCamelCase.

Method names are typically verbs or verb phrases. For example, sendMessage or stop.

Underscores may appear in JUnit test method names to separate logical components of the name, with each component written in lowerCamelCase. One typical pattern is <methodUnderTest>_<state>, for example pop_emptyStack. There is no One Correct Way to name test methods.

5.2.4 Constant names

Constant names use CONSTANT_CASE: all uppercase letters, with each word separated from the next by a single underscore. But what is a constant, exactly?

Constants are static final fields whose contents are deeply immutable and whose methods have no detectable side effects. This includes primitives, Strings, immutable types, and immutable collections of immutable types. If any of the instance's observable state can change, it is not a constant. Merely intending to never mutate the object is not enough. Examples:

// Constants
static final int NUMBER = 5;
static final ImmutableList<String> NAMES = ImmutableList.of("Ed", "Ann");
static final ImmutableMap<String, Integer> AGES = ImmutableMap.of("Ed", 35, "Ann", 32);
static final Joiner COMMA_JOINER = Joiner.on(','); // because Joiner is immutable
static final SomeMutableType[] EMPTY_ARRAY = {};
enum SomeEnum { ENUM_CONSTANT }

// Not constants
static String nonFinal = "non-final";
final String nonStatic = "non-static";
static final Set<String> mutableCollection = new HashSet<String>();
static final ImmutableSet<SomeMutableType> mutableElements = ImmutableSet.of(mutable);
static final ImmutableMap<String, SomeMutableType> mutableValues =
    ImmutableMap.of("Ed", mutableInstance, "Ann", mutableInstance2);
static final Logger logger = Logger.getLogger(MyClass.getName());
static final String[] nonEmptyArray = {"these", "can", "change"};
These names are typically nouns or noun phrases.

5.2.5 Non-constant field names

Non-constant field names (static or otherwise) are written in lowerCamelCase.

These names are typically nouns or noun phrases. For example, computedValues or index.

5.2.6 Parameter names

Parameter names are written in lowerCamelCase.

One-character parameter names in public methods should be avoided.

5.2.7 Local variable names

Local variable names are written in lowerCamelCase.

Even when final and immutable, local variables are not considered to be constants, and should not be styled as constants.

5.2.8 Type variable names

Each type variable is named in one of two styles:

A single capital letter, optionally followed by a single numeral (such as E, T, X, T2)
A name in the form used for classes (see Section 5.2.2, Class names), followed by the capital letter T (examples: RequestT, FooBarT).
5.3 Camel case: defined

Sometimes there is more than one reasonable way to convert an English phrase into camel case, such as when acronyms or unusual constructs like "IPv6" or "iOS" are present. To improve predictability, Google Style specifies the following (nearly) deterministic scheme.

Beginning with the prose form of the name:

Convert the phrase to plain ASCII and remove any apostrophes. For example, "Müller's algorithm" might become "Muellers algorithm".
Divide this result into words, splitting on spaces and any remaining punctuation (typically hyphens).
Recommended: if any word already has a conventional camel-case appearance in common usage, split this into its constituent parts (e.g., "AdWords" becomes "ad words"). Note that a word such as "iOS" is not really in camel case per se; it defies any convention, so this recommendation does not apply.
Now lowercase everything (including acronyms), then uppercase only the first character of:
... each word, to yield upper camel case, or
... each word except the first, to yield lower camel case
Finally, join all the words into a single identifier.
Note that the casing of the original words is almost entirely disregarded. Examples:

Prose form	Correct	Incorrect
"XML HTTP request"	XmlHttpRequest	XMLHTTPRequest
"new customer ID"	newCustomerId	newCustomerID
"inner stopwatch"	innerStopwatch	innerStopWatch
"supports IPv6 on iOS?"	supportsIpv6OnIos	supportsIPv6OnIOS
"YouTube importer"	YouTubeImporter
YoutubeImporter*	
*Acceptable, but not recommended.

Note: Some words are ambiguously hyphenated in the English language: for example "nonempty" and "non-empty" are both correct, so the method names checkNonempty and checkNonEmpty are likewise both correct.

6 Programming Practices

6.1 @Override: always used

A method is marked with the @Override annotation whenever it is legal. This includes a class method overriding a superclass method, a class method implementing an interface method, and an interface method respecifying a superinterface method.

Exception: @Override may be omitted when the parent method is @Deprecated.

6.2 Caught exceptions: not ignored

Except as noted below, it is very rarely correct to do nothing in response to a caught exception. (Typical responses are to log it, or if it is considered "impossible", rethrow it as an AssertionError.)

When it truly is appropriate to take no action whatsoever in a catch block, the reason this is justified is explained in a comment.

try {
  int i = Integer.parseInt(response);
  return handleNumericResponse(i);
} catch (NumberFormatException ok) {
  // it's not numeric; that's fine, just continue
}
return handleTextResponse(response);
Exception: In tests, a caught exception may be ignored without comment if its name is or begins with expected. The following is a very common idiom for ensuring that the code under test does throw an exception of the expected type, so a comment is unnecessary here.

try {
  emptyStack.pop();
  fail();
} catch (NoSuchElementException expected) {
}
6.3 Static members: qualified using class

When a reference to a static class member must be qualified, it is qualified with that class's name, not with a reference or expression of that class's type.

Foo aFoo = ...; Foo . aStaticMethod (); // bom aFoo . aStaticMethod (); // bad somethingThatYieldsAFoo (). aStaticMethod (); // muito mal 
 
 
 
6.4 Finalizadores: não utilizados

É extremamente raro substituir .Object.finalize

Dica: não faça isso. Se você absolutamente deve, primeiro leia e compreenda o Eficaz Java Item 7, "Evite Finalizers", com muita atenção, e então não faça isso.

7 Javadoc

7.1 Formatação

7.1.1 Forma geral

A formatação básica dos blocos Javadoc é vista neste exemplo:

/ ** 
 * Múltiplas linhas de texto Javadoc estão escritas aqui, 
 * envolto normalmente ... 
 * / método public int ( String p1 ) { ... }
    
... ou neste exemplo de linha única:

/ ** Um pouco especial de Javadoc. * /
A forma básica é sempre aceitável. O formulário de linha única pode ser substituído quando a totalidade do bloco Javadoc (incluindo marcadores de comentários) pode caber em uma única linha. Observe que isso só se aplica quando não há tags de bloco, como @return.

7.1.2 Parágrafos

Uma linha em branco - isto é, uma linha que contém apenas o asterisco líder alinhado ( *) - aparece entre parágrafos e antes do grupo de tags de bloco se presente. Cada parágrafo, mas o primeiro <p>imediatamente antes da primeira palavra, sem espaço depois.

7.1.3 Tags de bloco

Qualquer um dos padrão "tags bloco" que são usados aparecem na ordem @param, @return, @throws, @deprecated, e esses quatro tipos nunca aparecem com uma descrição vazia. Quando uma etiqueta de bloco não se encaixa em uma única linha, as linhas de continuação são recortadas quatro (ou mais) espaços da posição do @.

7.2 O fragmento de resumo

Cada bloco Javadoc começa com um breve fragmento de resumo . Este fragmento é muito importante: é a única parte do texto que aparece em determinados contextos, como índices de classe e método.

Este é um fragmento - uma frase nominal ou frase verbal, não uma frase completa. Ele não começar com A {@code Foo} is a..., ou This method returns..., nem formar uma frase imperativa completa como Save the record.. No entanto, o fragmento é maiúscula e pontuada como se fosse uma frase completa.

Dica: um erro comum é escrever Javadoc simples no formulário /** @return the customer ID */. Isso é incorreto e deve ser alterado para /** Returns the customer ID. */.

7.3 Onde Javadoc é usado

No mínimo , Javadoc está presente para todas as publicaulas, e todos publicou protectedmembros dessa classe, com algumas exceções observadas abaixo.

O conteúdo adicional de Javadoc também pode estar presente, como explicado na Seção 7.3.4, Javadoc não requerido .

7.3.1 Exceção: métodos auto-explicativos

Javadoc is optional for "simple, obvious" methods like getFoo, in cases where there really and truly is nothing else worthwhile to say but "Returns the foo".

Important: it is not appropriate to cite this exception to justify omitting relevant information that a typical reader might need to know. For example, for a method named getCanonicalName, don't omit its documentation (with the rationale that it would say only /** Returns the canonical name. */) if a typical reader may have no idea what the term "canonical name" means!

7.3.2 Exception: overrides

Javadoc is not always present on a method that overrides a supertype method.

7.3.4 Non-required Javadoc

Other classes and members have Javadoc as needed or desired.

Sempre que um comentário de implementação seja usado para definir o propósito geral ou comportamento de uma classe ou membro, esse comentário é escrito como Javadoc em vez disso (usando /**).

O Javadoc não requerido não é estritamente necessário para seguir as regras de formatação das Seções 7.1.2, 7.1.3 e 7.2, embora, claro, seja recomendado.
