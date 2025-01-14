# Xenial Xmas Vigor

クリスマスをイメージした難解プログラミング言語です。  
クリスマスツリーのような形状のソースコードを記述し、25本のスタックと25種類のビットフラグを操作してプログラムを実行します。略称はXXVです。  
[C3 Advent Calender 2024](https://qiita.com/advent-calendar/2024/c3)の[21日目の記事](https://compositecomputer.club/blog/354fea2a-2f93-4864-9d5d-e038d77a7586)の題材として制作しました。

## Requirements

### Runtime
Releasesのjarを使用して実行する場合、JRE SE 21以上、またはそれに相当するJava実行環境が必要です。

### Development
ソースコードのコンパイルにはJDK SE 21以上、またはそれに相当するJava SDKが必要です。  
(古いJDKでコンパイルしたい場合は各自で置き換えをして下さい)

## Installation
[Releases](https://github.com/Bokume2/XenialXmasVigor/releases)に実行可能なjarを用意しています。  
ダウンロードして任意の場所に配置し、下記[Usage](https://github.com/Bokume2/XenialXmasVigor#usage)に沿って実行して下さい。

## Build
cloneしたリポジトリで以下の通り実行すると、releases内に実行可能なjarファイル`xxvlang.jar`が作成されます。  
Windows PowerShell等の環境では`@`から始まるオプションをダブルクオーテーションで囲むなどして下さい。  

```
cd releases
javac @javac_sources.txt
jar -c -f xxvlang.jar @jar_options.txt
```

## Usage
カレントディレクトリに[Releases](https://github.com/Bokume2/XenialXmasVigor/releases)からダウンロード、またはソースコードからビルドしたjarファイル`xxvlang.jar`が置かれているとき、以下のコマンドで`<source file>`に記述されたXXVソースコードを実行します。  

```
java -jar xxvlang.jar <source file>
```

このコマンドは、引数なしでjarを実行した場合にも表示されます。
## Syntax
ただいま**ドキュメントの整備中**です。

## Samples
- `merry.xxv`  
  実行すると`Merry Christmas!`の文字列が表示されます。

## Contact
バグ報告やリファクタリングの提案などは[Twitter(現X)](https://x.com/boku_renraku)等で遠慮無くお声掛け下さい。
