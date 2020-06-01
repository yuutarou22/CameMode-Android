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

## 参考文献
- [アクションバーを非表示にする](https://qiita.com/ikemura23/items/76e78132e6903c47c4d7)
- [マテリアルデザインアイコンを使用する](https://qiita.com/hoshiume11/items/b9925d105957d827011a)
