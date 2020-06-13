# CameMode-Android
CameModeのAndroid版アプリです。
旧CameModeはJava、当リポジトリはKotlinです。


## DB設計
[NiftyCloudMobileBackend](https://mbaas.nifcloud.com/)というmBaaSを利用する。

| カラム名 | 説明 | バリデーション |
----|----|----
| objectId | 自動付与 | なし（自動付与） |
| categoryRole | 希望種別 | 0,1,2のみ |
| displayName | CameModeでの表示名 | 最大文字数(未定) |
| photoImage | 撮影イメージ | 最大文字数(未定) |
| twitterId | TwitterのID | 英数字のみ |
| age | 年代 | 数値 |
| region | 地域 | 数値 |
| sex | 性別 | 数値 |
| charge | 有償無償 | 数値 |

追々、実装予定(instagram対応)
| カラム名 | 説明 | バリデーション |
----|----|----
| instagramId | InstagramのID | 英数字のみ |
| snsRole | SNS種別 | 0,1,2のみ |

## Issueに関して
Issueについては下記の記事を参考
- [GitHub の Issue 運用が助かるラベル 26 個 #Zaim](https://blog.zaim.co.jp/n/nca91e5bfb920)
- [Issuesのラベルを管理（編集）する方法](https://azunobu.hatenablog.com/entry/2015/09/22/143811)

## 参考文献
- [アクションバーを非表示にする](https://qiita.com/ikemura23/items/76e78132e6903c47c4d7)
- [マテリアルデザインアイコンを使用する](https://qiita.com/hoshiume11/items/b9925d105957d827011a)
- [override修飾子が非活性状態になる原因](https://stackoverflow.com/questions/56906990/why-do-i-get-the-message-redundant-overriding-method)
  - オーバーライドしただけのメソッドだからそりゃ冗長だよね。
- [companion objectについて](https://qiita.com/tkhs0604/items/261e94a42b7097dfd204)
  - シングルトンを作れる。けど、通常複数のクラスから参照するため結合度が高くなりやすい。
- [NCMB導入](https://github.com/NIFCLOUD-mbaas/KotlinDBdemoApp)
- [RadioButtonのカスタマイズ](http://yukimura1227.blog.fc2.com/blog-entry-11.html)
  - https://hirauchi-genta.com/kotlin-radiobutton/
- [Selector：状態を保持するXML形式のオブジェクト](https://developer.android.com/guide/topics/resources/drawable-resource#StateList)
  - res/drawable右クリック→new>drawable resource file>ファイル名入力しOK
