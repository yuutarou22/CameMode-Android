# CameMode-Android
CameModeのAndroid版アプリです。
[旧CameMode](https://github.com/yuutarou22/CameMode)はJava、当リポジトリはKotlinです。


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
Issueを使って、タスク管理を行う。下記の記事を参考にしている。
- [開発者のタスク管理をGitHubで行ったらうまくいった話](https://dev.classmethod.jp/articles/github-issue-driven-dev/)

ラベル設定については、下記の記事を参考にしている。
- [GitHub の Issue 運用が助かるラベル 26 個 #Zaim](https://blog.zaim.co.jp/n/nca91e5bfb920)
- [Issuesのラベルを管理（編集）する方法](https://azunobu.hatenablog.com/entry/2015/09/22/143811)

## 参考文献
- [BottomNavigationView](https://qiita.com/iKimishima/items/d44bb9cc2a1d04548fdd)
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
- [ConstraintLayoutでMATCH_PARENT利用時の想定外の挙動](https://qiita.com/ara_tack/items/68c07529c1477c56997f)
  - FrameLayoutだと要素が重なってしまうが、heightを0dpにし制約を加えることで空いた領域一杯に広げられる。
- [Fragmentの初期化はonViewCreatedかonActivityCreated](https://medium.com/@star_zero/fragment%E3%81%AE%E5%88%9D%E6%9C%9F%E5%8C%96%E3%81%AFonviewcreated%E3%81%8Bonactivitycreated%E3%81%A7-b9646c36680c)
  - ちなみに、ライフサイクルは以下の順番
    - onAttach
    - onCreate
    - onCreateView
    - onViewCreated
- [RecyclerView をレイアウトに追加する](https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=ja#workflow)
  - 結構行き詰まった。特に、取得した後のListの扱い方。
  - ここも参考→https://qiita.com/Todate/items/297bc3e4d0f3d2477ed3

## ToDo(あとで消す)
- かんたん検索画面のレイアウト修正
- 実際に検索し、情報を取得する
- 画面遷移
  - 検索後の検索結果画面
  - 詳細検索画面
- チュートリアル画面の作成
- 使い方をライブラリを使用して実装
