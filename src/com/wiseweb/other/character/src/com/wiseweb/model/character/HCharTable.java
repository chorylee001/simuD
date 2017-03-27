/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.character.src.com.wiseweb.model.character;

/**
 * @author Teary Wang on 2016/10/18.
 */
public class HCharTable {

	private static final char[] hfStoke1 = { '\u4E00','\u4E59' };
	private static final char[] hfStoke2 = {
		'\u4E01','\u4E03','\u4E43','\u4E5D','\u4E86','\u4E8C','\u4EBA',
		'\u513F','\u5165','\u516B','\u51E0','\u5200','\u529B','\u5341',
		'\u535C','\u5382','\u53C8'
	};
	private static final char[] hfStoke3 = {
		'\u4E07','\u4E08','\u4E09','\u4E0A','\u4E0B','\u4E0E','\u4E2A',
		'\u4E38','\u4E45','\u4E48','\u4E49','\u4E4B','\u4E5E','\u4E5F',
		'\u4E60','\u4E61','\u4E8E','\u4E8F','\u4EA1','\u4EBF','\u51E1',
		'\u5203','\u52FA','\u5343','\u536B','\u53C9','\u53CA','\u53E3',
		'\u571F','\u58EB','\u5915','\u5927','\u5973','\u5B50','\u5BF8',
		'\u5C0F','\u5C38','\u5C71','\u5DDD','\u5DE5','\u5DF1','\u5DF2',
		'\u5DFE','\u5E72','\u5E7F','\u5F13','\u624D','\u95E8','\u98DE',
		'\u9A6C'
	};
	private static final char[] hfStoke4 = {
		'\u4E0D','\u4E11','\u4E13','\u4E2D','\u4E30','\u4E39','\u4E3A',
		'\u4E4C','\u4E4F','\u4E66','\u4E88','\u4E91','\u4E92','\u4E94',
		'\u4E95','\u4EC0','\u4EC1','\u4EC5','\u4EC6','\u4EC7','\u4ECA',
		'\u4ECB','\u4ECD','\u4ECE','\u4ED3','\u4EE5','\u5141','\u5143',
		'\u516C','\u516D','\u5185','\u5188','\u51E4','\u51F6','\u5206',
		'\u5207','\u529D','\u529E','\u52FE','\u52FF','\u5300','\u5316',
		'\u5339','\u533A','\u5347','\u5348','\u5385','\u5386','\u53CB',
		'\u53CC','\u53CD','\u5929','\u592A','\u592B','\u5B54','\u5C11',
		'\u5C24','\u5C3A','\u5C6F','\u5DE8','\u5DF4','\u5E01','\u5E7B',
		'\u5F00','\u5F15','\u5FC3','\u5FC6','\u6237','\u624B','\u624E',
		'\u652F','\u6587','\u6597','\u65A4','\u65B9','\u65E0','\u65E5',
		'\u6708','\u6728','\u6B20','\u6B62','\u6BD4','\u6BDB','\u6C0F',
		'\u6C14','\u6C34','\u706B','\u722A','\u7236','\u7247','\u7259',
		'\u725B','\u72AC','\u738B','\u74E6','\u827A','\u89C1','\u8BA1',
		'\u8BA2','\u8BA4','\u8D1D','\u8F66','\u957F','\u961F','\u98CE'
	};
	private static final char[] hfStoke5 = {
		'\u4E14','\u4E16','\u4E18','\u4E19','\u4E1A','\u4E1B','\u4E1C',
		'\u4E1D','\u4E3B','\u4E4E','\u4E50','\u4ED4','\u4ED6','\u4ED7',
		'\u4ED8','\u4ED9','\u4EE3','\u4EE4','\u4EEA','\u4EEC','\u5144',
		'\u5170','\u518C','\u5199','\u51AC','\u51FA','\u51FB','\u520A',
		'\u529F','\u52A0','\u52A1','\u5305','\u5306','\u5317','\u534A',
		'\u5360','\u5361','\u5370','\u5389','\u53BB','\u53D1','\u53E4',
		'\u53E5','\u53E6','\u53E8','\u53EA','\u53EB','\u53EC','\u53EE',
		'\u53EF','\u53F0','\u53F2','\u53F3','\u53F6','\u53F7','\u53F8',
		'\u53F9','\u53FC','\u56DB','\u5723','\u5904','\u5916','\u592E',
		'\u5931','\u5934','\u5974','\u5976','\u5B55','\u5B81','\u5B83',
		'\u5BF9','\u5C3C','\u5DE6','\u5DE7','\u5E02','\u5E03','\u5E05',
		'\u5E73','\u5E7C','\u5F52','\u5FC5','\u6251','\u6252','\u6253',
		'\u6254','\u65A5','\u65E6','\u65E7','\u672A','\u672B','\u672C',
		'\u672F','\u6B63','\u6BCD','\u6C11','\u6C38','\u6C41','\u6C47',
		'\u6C49','\u706D','\u72AF','\u7389','\u74DC','\u7518','\u751F',
		'\u7528','\u7529','\u7530','\u7531','\u7532','\u7533','\u7535',
		'\u767D','\u76AE','\u76EE','\u77DB','\u77F3','\u793A','\u793C',
		'\u79BE','\u7A74','\u7ACB','\u7EA0','\u8282','\u8BA8','\u8BA9',
		'\u8BAD','\u8BAE','\u8BAF','\u8BB0','\u8F67','\u8FB9','\u8FBD',
		'\u95EA','\u9965','\u9E1F','\u9F99'
	};
	private static final char[] hfStoke6 = {
		'\u4E22','\u4E52','\u4E53','\u4E54','\u4E70','\u4E89','\u4E9A',
		'\u4EA4','\u4EA6','\u4EA7','\u4EF0','\u4EF6','\u4EF7','\u4EFB',
		'\u4EFD','\u4EFF','\u4F01','\u4F0D','\u4F0F','\u4F10','\u4F11',
		'\u4F17','\u4F18','\u4F19','\u4F1A','\u4F1E','\u4F1F','\u4F20',
		'\u4F24','\u4F2A','\u4F3C','\u5145','\u5146','\u5148','\u5149',
		'\u5168','\u5171','\u5173','\u5174','\u518D','\u519B','\u519C',
		'\u51B0','\u51B2','\u51B3','\u5211','\u5212','\u5217','\u5218',
		'\u5219','\u521A','\u521B','\u52A3','\u52A8','\u5320','\u534E',
		'\u534F','\u5371','\u538B','\u538C','\u5403','\u5404','\u5408',
		'\u5409','\u540A','\u540C','\u540D','\u540E','\u5410','\u5411',
		'\u5413','\u5417','\u5438','\u56DE','\u56E0','\u56E2','\u5728',
		'\u5730','\u573A','\u573E','\u58EE','\u591A','\u5938','\u5939',
		'\u593A','\u5978','\u5979','\u597D','\u5982','\u5984','\u5987',
		'\u5988','\u5B57','\u5B58','\u5B59','\u5B85','\u5B87','\u5B88',
		'\u5B89','\u5BFA','\u5BFB','\u5BFC','\u5C16','\u5C18','\u5C3D',
		'\u5C7F','\u5C81','\u5C82','\u5DDE','\u5DE1','\u5DE9','\u5E06',
		'\u5E08','\u5E74','\u5E76','\u5E84','\u5E86','\u5EF6','\u5F02',
		'\u5F0F','\u5F53','\u5FD9','\u620F','\u6210','\u6258','\u625B',
		'\u6263','\u6267','\u6269','\u626B','\u626C','\u6536','\u65E8',
		'\u65E9','\u65EC','\u66F2','\u6709','\u6731','\u6734','\u6735',
		'\u673A','\u673D','\u6740','\u6742','\u6743','\u6B21','\u6B22',
		'\u6B64','\u6BD5','\u6C57','\u6C5F','\u6C60','\u6C61',
		'\u6C64','\u706F','\u7070','\u7237','\u767E','\u7AF9','\u7C73',
		'\u7EA2','\u7EA4','\u7EA6','\u7EA7','\u7EAA','\u7F51','\u7F8A',
		'\u7FBD','\u8001','\u8003','\u800C','\u8033','\u8089','\u808C',
		'\u81E3','\u81EA','\u81F3','\u820C','\u821F','\u8292',
		'\u829D','\u866B','\u8840','\u884C','\u8863','\u897F','\u89C2',
		'\u8BB2','\u8BB8','\u8BBA','\u8BBD','\u8BBE','\u8BBF','\u8D1E',
		'\u8D1F','\u8F68','\u8FBE','\u8FC1','\u8FC5','\u8FC7','\u8FC8',
		'\u90A3','\u90AA','\u95ED','\u95EE','\u95EF','\u9632','\u9633',
		'\u9634','\u9635','\u9636','\u9875','\u9A70','\u9F50'
	};
	private static final char[] hfStoke7 = {
		'\u4E24','\u4E25','\u4E32','\u4E3D','\u4E71','\u4EA9','\u4F2F',
		'\u4F30','\u4F34','\u4F36','\u4F38','\u4F46','\u4F4D','\u4F4E',
		'\u4F4F','\u4F53','\u4F55','\u4F59','\u4F5B','\u4F5C','\u4F60',
		'\u4F63','\u514B','\u514D','\u5175','\u51B5','\u51B6','\u51B7',
		'\u51BB','\u521D','\u5220','\u5224','\u5229','\u522B','\u52A9',
		'\u52AA','\u52AB','\u52B1','\u52B2','\u52B3','\u533B','\u5373',
		'\u5374','\u5375','\u53BF','\u541B','\u541E','\u5426','\u5427',
		'\u5428','\u5429','\u542B','\u542C','\u542F','\u5434','\u5435',
		'\u5439','\u543C','\u5440','\u5446','\u5448','\u544A','\u5458',
		'\u545C','\u56ED','\u56F0','\u56F4','\u5740','\u5747','\u574A',
		'\u574F','\u5750','\u5751','\u5757','\u575A','\u575B','\u575D',
		'\u575F','\u58F0','\u58F3','\u5996','\u5999','\u59A5','\u59A8',
		'\u5B5D','\u5B8B','\u5B8C','\u5B8F','\u5BFF','\u5C3E','\u5C3F',
		'\u5C40','\u5C42','\u5C94','\u5C97','\u5C9B','\u5E0C','\u5E10',
		'\u5E8A','\u5E8F','\u5E93','\u5E94','\u5F03','\u5F04','\u5F1F',
		'\u5F20','\u5F62','\u5F79','\u5F7B','\u5FCC','\u5FCD','\u5FD7',
		'\u5FD8','\u5FE7','\u5FEB','\u6000','\u6211','\u6212','\u626D',
		'\u626E','\u626F','\u6270','\u6276','\u6279','\u627E','\u6280',
		'\u6284','\u628A','\u6293','\u6295','\u6296','\u6297','\u6298',
		'\u629A','\u629B','\u62A2','\u62A4','\u62A5','\u62D2','\u6539',
		'\u653B','\u65F1','\u65F6','\u65F7','\u66F4','\u6746','\u674E',
		'\u674F','\u6750','\u6751','\u675C','\u675F','\u6760','\u6761',
		'\u6765','\u6768','\u6781','\u6B65','\u6B7C','\u6BCF','\u6C42',
		'\u6C6A','\u6C7D','\u6C83','\u6C88','\u6C89','\u6C99','\u6C9F',
		'\u6CA1','\u6CDB','\u7075','\u7076','\u707E','\u707F','\u7262',
		'\u72B6','\u72B9','\u72C2','\u7537','\u7597','\u7682','\u76EF',
		'\u793E','\u79C0','\u79C1','\u79C3','\u7A76','\u7A77','\u7CFB',
		'\u7EAF','\u7EB1','\u7EB2','\u7EB3','\u7EB5','\u7EB7','\u7EB8',
		'\u7EB9','\u7EBA','\u7EBD','\u809A','\u809D','\u80A0','\u826F',
		'\u82A6','\u82AC','\u82B1','\u82B3','\u82B9','\u82BD','\u82CD',
		'\u82CF','\u8865','\u89D2','\u8A00','\u8BC1','\u8BC4','\u8BC6',
		'\u8BC9','\u8BCA','\u8BCD','\u8BD1','\u8C37','\u8C46','\u8D21',
		'\u8D22','\u8D64','\u8D70','\u8DB3','\u8EAB','\u8F9B','\u8FB0',
		'\u8FCE','\u8FD0','\u8FD1','\u8FD4','\u8FD8','\u8FD9','\u8FDB',
		'\u8FDC','\u8FDD','\u8FDE','\u8FDF','\u90AE','\u90BB','\u91CC',
		'\u9488','\u9489','\u95F2','\u95F4','\u95F7','\u963B','\u963F',
		'\u9644','\u9645','\u9646','\u9648','\u996D','\u996E','\u9A71',
		'\u9A73','\u9A74','\u9E21','\u9EA6','\u9F9F'
	};
	private static final char[] hfStoke8 = {
		'\u4E27','\u4E56','\u4E73','\u4E8B','\u4E9B','\u4EAB','\u4EAC',
		'\u4F69','\u4F73','\u4F7F','\u4F84','\u4F8B','\u4F8D','\u4F9B',
		'\u4F9D','\u4FA6','\u4FA7','\u4FA8','\u5154','\u5176','\u5177',
		'\u5178','\u51C0','\u51ED','\u51EF','\u522E','\u5230','\u5236',
		'\u5237','\u5238','\u523A','\u523B','\u5242','\u52BF','\u5355',
		'\u5356','\u5367','\u5377','\u5395','\u53C2','\u53D4','\u53D6',
		'\u53D7','\u53D8','\u5462','\u5468','\u5473','\u547C','\u547D',
		'\u548C','\u548F','\u5490','\u56FA','\u56FD','\u56FE','\u5761',
		'\u5766','\u5782','\u5783','\u5784','\u5907','\u591C','\u5947',
		'\u5949','\u594B','\u5954','\u59B9','\u59BB','\u59CB','\u59D0',
		'\u59D1','\u59D3','\u59D4','\u5B5F','\u5B63','\u5B64','\u5B66',
		'\u5B97','\u5B98','\u5B99','\u5B9A','\u5B9C','\u5B9D','\u5B9E',
		'\u5BA1','\u5C1A','\u5C45','\u5C48','\u5C4A','\u5CA9','\u5CAD',
		'\u5CB8','\u5E16','\u5E18','\u5E1C','\u5E78','\u5E95','\u5E97',
		'\u5E99','\u5E9C','\u5E9F','\u5EFA','\u5F26','\u5F55','\u5F7C',
		'\u5F80','\u5F81','\u5F84','\u5FE0','\u5FF5','\u5FFD','\u6001',
		'\u6015','\u6016','\u601C','\u602A','\u6216','\u623F',
		'\u6240','\u627F','\u62AB','\u62AC','\u62B1','\u62B5','\u62B9',
		'\u62BC','\u62BD','\u62C5','\u62C6','\u62C9','\u62CC','\u62CD',
		'\u62D0','\u62D4','\u62D6','\u62D8','\u62DB','\u62E2','\u62E3',
		'\u62E5','\u62E6','\u62E8','\u62E9','\u653E','\u65A7','\u65A9',
		'\u65FA','\u6602','\u6606','\u660C','\u660E','\u660F','\u6613',
		'\u670B','\u670D','\u676F','\u6770','\u677E','\u677F','\u6784',
		'\u6790','\u6795','\u6797','\u679C','\u679D','\u67A3','\u67AA',
		'\u67DC','\u6B23','\u6B27','\u6B66','\u6CAB','\u6CB3','\u6CB8',
		'\u6CB9','\u6CBB','\u6CBE','\u6CBF','\u6CC4','\u6CCA','\u6CD5',
		'\u6CE1','\u6CE2','\u6CE5','\u6CE8','\u6CEA','\u6CF3','\u6CFB',
		'\u6CFC','\u6CFD','\u6D45','\u7089','\u708A','\u708E','\u7092',
		'\u7095','\u722C','\u7238','\u7248','\u7267','\u7269','\u72D0',
		'\u72D7','\u73A9','\u73AF','\u73B0','\u753B','\u7545','\u7684',
		'\u76F2','\u76F4','\u77E5','\u77FF','\u7801','\u79C6','\u7A7A',
		'\u7EBF','\u7EC3','\u7EC4','\u7EC6','\u7EC7','\u7EC8','\u7ECD',
		'\u7ECF','\u7F57','\u8005','\u8083','\u80A1','\u80A2','\u80A4',
		'\u80A5','\u80A9','\u80AF','\u80B2','\u80BA','\u80BE','\u80BF',
		'\u80C0','\u80C1','\u820D','\u8270','\u82D7','\u82E5','\u82E6',
		'\u82F1','\u82F9','\u8302','\u8303','\u8304','\u8305','\u830E',
		'\u864E','\u864F','\u8868','\u886B','\u886C','\u89C4','\u89C6',
		'\u8BD5','\u8BD7','\u8BDA','\u8BDD','\u8BDE','\u8BE2','\u8BE5',
		'\u8BE6','\u8D23','\u8D24','\u8D25','\u8D27','\u8D28','\u8D29',
		'\u8D2A','\u8D2B','\u8D2D','\u8D2F','\u8F6C','\u8F6E','\u8F6F',
		'\u8F70','\u8FEB','\u8FF0','\u90CA','\u90CE','\u90D1','\u91C7',
		'\u91D1','\u9493','\u95F8','\u95F9','\u964D','\u9650','\u9655',
		'\u96B6','\u96E8','\u9752','\u975E','\u9876','\u9877','\u9970',
		'\u9971','\u9972','\u9A76','\u9A7B','\u9A7C','\u9A7E','\u9C7C',
		'\u9E23','\u9F7F'
	};
	private static final char[] hfStoke9 = {
		'\u4E34','\u4E3E','\u4EAD','\u4EAE','\u4EB2','\u4FAE','\u4FB5',
		'\u4FBF','\u4FC3','\u4FCA','\u4FD7','\u4FD8','\u4FDD','\u4FE1',
		'\u4FE9','\u4FED','\u4FEE','\u517B','\u5192','\u51A0','\u5243',
		'\u524A','\u524D','\u5251','\u52C7','\u52C9','\u5357','\u5378',
		'\u5398','\u539A','\u53D9','\u53DB','\u54AC','\u54B1','\u54B3',
		'\u54B8','\u54BD','\u54C0','\u54C1','\u54C4','\u54C8','\u54CD',
		'\u54D1','\u54D7','\u54EA','\u578B','\u5792','\u57A6','\u57AB',
		'\u57AE','\u57CE','\u590D','\u594F','\u5956','\u59DC','\u59E5',
		'\u59E8','\u59FB','\u59FF','\u5A01','\u5A03','\u5A07','\u5B69',
		'\u5BA2','\u5BA3','\u5BA4','\u5BAA','\u5BAB','\u5C01','\u5C06',
		'\u5C1D','\u5C4B','\u5CE1','\u5DEE','\u5DF7','\u5E1D','\u5E26',
		'\u5E2E','\u5EA6','\u5EAD','\u5F2F','\u5F85','\u5F88','\u5F8B',
		'\u600E','\u6012','\u601D','\u6020','\u6025','\u6028','\u603B',
		'\u6052','\u6062','\u6068','\u6070','\u607C','\u6218','\u6241',
		'\u62DC','\u62EC','\u62F4','\u62FC','\u62FE','\u6301','\u6302',
		'\u6307','\u6309','\u630E','\u6311','\u6316','\u6320','\u6321',
		'\u6323','\u6324','\u6325','\u632A','\u633A','\u653F','\u6545',
		'\u65BD','\u65E2','\u661F','\u6620','\u6625','\u6628','\u662F',
		'\u663C','\u663E','\u67AF','\u67B6','\u67C4','\u67CF','\u67D0',
		'\u67D3','\u67D4','\u67E5','\u67F1','\u67F3','\u67FF','\u6807',
		'\u680B','\u680F','\u6811','\u6B6A','\u6B83','\u6B8B','\u6BB5',
		'\u6BD2','\u6CC9','\u6D01','\u6D0B','\u6D12','\u6D17','\u6D1E',
		'\u6D25','\u6D2A','\u6D32','\u6D3B','\u6D3D','\u6D3E','\u6D47',
		'\u6D4A','\u6D4B','\u6D4E','\u6D51','\u6D53','\u70AD','\u70AE',
		'\u70B8','\u70B9','\u70BC','\u70C2','\u7272','\u7275','\u72E0',
		'\u72E1','\u72EC','\u72ED','\u72EE','\u72F1','\u73BB','\u73CD',
		'\u751A','\u754C','\u754F','\u75A4','\u75AB','\u75AE','\u75AF',
		'\u7686','\u7687','\u76C6','\u76C8','\u76F8','\u76FC','\u76FE',
		'\u7701','\u7709','\u770B','\u7728','\u77E9','\u780C','\u780D',
		'\u7814','\u7816','\u7956','\u795D','\u795E','\u79CB','\u79CD',
		'\u79D1','\u79D2','\u7A7F','\u7A81','\u7A83','\u7AD6','\u7AFF',
		'\u7C7B','\u7ED1','\u7ED2','\u7ED3','\u7ED5','\u7ED8','\u7ED9',
		'\u7EDC','\u7EDD','\u7EDE','\u7EDF','\u7F38','\u7F5A','\u7F8E',
		'\u800D','\u8010','\u80C3','\u80C6','\u80CC','\u80D6','\u80DC',
		'\u80DE','\u80E1','\u8109','\u8327','\u832B','\u8336','\u8349',
		'\u8350','\u8352','\u8361','\u8363','\u836F','\u8679','\u867D',
		'\u867E','\u8680','\u8681','\u8682','\u8884','\u8981','\u89C8',
		'\u89C9','\u8BED','\u8BEF','\u8BF1','\u8BF4','\u8BF5','\u8D31',
		'\u8D34','\u8D35','\u8D37','\u8D38','\u8D39','\u8D3A','\u8D74',
		'\u8D75','\u8DB4','\u8F7B','\u8FF7','\u8FF9','\u8FFD','\u9000',
		'\u9001','\u9002','\u9003','\u9006','\u9009','\u91CD','\u949E',
		'\u949F','\u94A2','\u94A5','\u94A9','\u95FB','\u9600','\u9601',
		'\u9661','\u9662','\u9664','\u9669','\u9762','\u9769','\u97F3',
		'\u9879','\u987A','\u987B','\u98DF','\u9976','\u997A','\u997C',
		'\u9996','\u9999','\u9A82','\u9A84','\u9A86','\u9AA8',
		'\u9E26'
	};
	private static final char[] hfStoke10 = {
		'\u4E58','\u4FEF','\u4FF1','\u500D','\u5012','\u5018','\u5019',
		'\u501A','\u501F','\u5021','\u5026','\u503A','\u503C','\u503E',
		'\u5065','\u515A','\u517C','\u51A4','\u51C6','\u51C9','\u5256',
		'\u5265','\u5267','\u532A','\u539F','\u54E5','\u54E8','\u54ED',
		'\u54F2','\u5507','\u5509','\u5510','\u5524','\u554A','\u5706',
		'\u57CB','\u58F6','\u590F','\u5957','\u5A18','\u5A31','\u5BB0',
		'\u5BB3','\u5BB4','\u5BB5','\u5BB6','\u5BB9','\u5BBD','\u5BBE',
		'\u5C04','\u5C51','\u5C55','\u5CF0','\u5E2D','\u5EA7','\u5F31',
		'\u5F90','\u5F92','\u604B','\u6050','\u6069','\u606D','\u606F',
		'\u6073','\u6076','\u6084','\u6094','\u609F','\u60A6','\u6247',
		'\u62F3','\u62FF','\u6328','\u632F','\u633D','\u6346','\u6349',
		'\u634E','\u634F','\u6350','\u6355','\u635E','\u635F','\u6361',
		'\u6362','\u6548','\u654C','\u6599','\u65C1','\u65C5','\u6643',
		'\u664B','\u664C','\u6652','\u6653','\u6655','\u6717','\u67F4',
		'\u6817','\u6821','\u682A','\u6837','\u6838','\u6839','\u683C',
		'\u683D','\u6842','\u6843','\u6846','\u6848','\u684C','\u6850',
		'\u6851','\u6863','\u6865','\u6868','\u6B8A','\u6BD9','\u6C27',
		'\u6CF0','\u6D41','\u6D46','\u6D59','\u6D69','\u6D6A','\u6D6E',
		'\u6D74','\u6D77','\u6D78','\u6D82','\u6D88','\u6D89','\u6D8C',
		'\u6D9B','\u6D9D','\u6DA6','\u6DA8','\u70C8','\u70D8','\u70DB',
		'\u70DF','\u70E4','\u70E6','\u70E7','\u70EB','\u70ED','\u7231',
		'\u7239','\u7279','\u727A','\u72F8','\u72FC','\u73E0','\u73ED',
		'\u74F6','\u7559','\u755C','\u75B2','\u75BC','\u75BE','\u75C5',
		'\u75C7','\u76B1','\u76CA','\u76CF','\u76D0','\u76D1','\u771F',
		'\u7720','\u7834','\u7840','\u7965','\u79BB','\u79D8','\u79DF',
		'\u79E4','\u79E7','\u79E9','\u79EF','\u79F0','\u7A84','\u7AD9',
		'\u7ADE','\u7B0B','\u7B11','\u7B14','\u7C89','\u7D20','\u7D22',
		'\u7D27','\u7EE2','\u7EE3','\u7EE7','\u7F3A','\u7F62','\u7F9E',
		'\u7FC1','\u7FC5','\u8015','\u8017','\u803B','\u803D','\u80F3',
		'\u80F6','\u80F8','\u80FD','\u8102','\u8106','\u810A','\u810F',
		'\u8111','\u81ED','\u81F4','\u822A','\u822C','\u8230','\u8231',
		'\u8273','\u8377','\u83AB','\u83B2','\u83B7','\u8651','\u868A',
		'\u8695','\u888D','\u8896','\u889C','\u88AB','\u8BF7',
		'\u8BF8','\u8BFB','\u8BFE','\u8C01','\u8C03','\u8C05','\u8C08',
		'\u8C0A','\u8D3C','\u8D3F','\u8D44','\u8D76','\u8D77','\u8EAC',
		'\u8F7D','\u8F7F','\u8F83','\u8FB1','\u900F','\u9010','\u9012',
		'\u9014','\u9017','\u901A','\u901D','\u901F','\u9020','\u9022',
		'\u90E8','\u90FD','\u914D','\u9152','\u94B1','\u94B3','\u94BB',
		'\u94C1','\u94C3','\u94C5','\u9605','\u966A','\u9675','\u9676',
		'\u9677','\u96BE','\u987D','\u987E','\u987F','\u9882','\u9884',
		'\u997F','\u9A8C','\u9AD8','\u9E2D'
	};
	private static final char[] hfStoke11 = {
		'\u5047','\u504F','\u505A','\u505C','\u5076','\u5077','\u507F',
		'\u517D','\u51CF','\u51D1','\u526A','\u526F','\u52D2','\u5319',
		'\u552E','\u552F','\u5531','\u5544','\u5546','\u5566','\u5708',
		'\u57DF','\u57F9','\u57FA','\u5802','\u5806','\u5835','\u591F',
		'\u5A46','\u5A5A','\u5A76','\u5BBF','\u5BC4','\u5BC6','\u5BC7',
		'\u5C60','\u5D07','\u5D16','\u5D2D','\u5E38','\u5EB7','\u5EB8',
		'\u5ECA','\u5F39','\u5F69','\u5F97','\u6089','\u60A0','\u60A3',
		'\u60A8','\u60AC','\u60BC','\u60C5','\u60CA','\u60D5','\u60DC',
		'\u60E7','\u60E8','\u60ED','\u60EF','\u621A','\u6367','\u636E',
		'\u6377','\u6380','\u6388','\u6389','\u638F','\u6392','\u6398',
		'\u63A0','\u63A2','\u63A5','\u63A7','\u63A8','\u63A9','\u63CF',
		'\u654F','\u6551','\u6559','\u6562','\u659C','\u65AD','\u65CB',
		'\u65CF','\u665A','\u6668','\u671B','\u6876','\u6881','\u6885',
		'\u68A2','\u68A6','\u68A8','\u68AF','\u68B0','\u68B3','\u68C0',
		'\u6B32','\u6BEB','\u6DB2','\u6DCB','\u6DD8','\u6DE1','\u6DF1',
		'\u6DF7','\u6DF9','\u6DFB','\u6E05','\u6E10','\u6E14','\u6E17',
		'\u6E20','\u723D','\u7281','\u730E','\u731B','\u731C','\u732A',
		'\u732B','\u7387','\u7403','\u7406','\u751C','\u7565','\u75D2',
		'\u75D5','\u76D2','\u76D6','\u76D7','\u76D8','\u76DB','\u772F',
		'\u773C','\u7740','\u7741','\u7968','\u7978','\u79FB','\u7A91',
		'\u7ADF','\u7AE0','\u7B1B','\u7B26','\u7B28','\u7B2C','\u7B3C',
		'\u7C92','\u7C97','\u7C98','\u7D2F','\u7EE9','\u7EEA','\u7EED',
		'\u7EF3','\u7EF4','\u7EF5','\u7EF8','\u7EFF','\u804B','\u804C',
		'\u8116','\u811A','\u8131','\u8138','\u8239','\u83CA','\u83CC',
		'\u83DC','\u83E0','\u8404','\u840C','\u840D','\u841D','\u8425',
		'\u8457','\u865A','\u86C7','\u86CB','\u8854','\u888B','\u88AD',
		'\u8C0B','\u8C0E','\u8C1C','\u8C61','\u8DC3','\u8DDD','\u8F85',
		'\u8F86','\u902E','\u91CE','\u94DC','\u94F2','\u94F6','\u9686',
		'\u968F','\u9690','\u96C0','\u96EA','\u9886','\u9888','\u9985',
		'\u9986','\u9A91','\u9E3D','\u9E7F','\u9EBB','\u9EC4'
	};
	private static final char[] hfStoke12 = {
		'\u5085','\u508D','\u50A8','\u50B2','\u5269','\u5272','\u535A',
		'\u53A6','\u53A8','\u5582','\u5584','\u5587','\u5589','\u558A',
		'\u5598','\u559C','\u559D','\u55B7','\u5821','\u5824','\u582A',
		'\u5854','\u5965','\u5AC2','\u5BCC','\u5BD2','\u5C0A','\u5C31',
		'\u5C5E','\u5C61','\u5E3D','\u5E45','\u5F3A','\u5FA1','\u5FAA',
		'\u60B2','\u60D1','\u60E0','\u60E9','\u60F0','\u60F9','\u6109',
		'\u6124','\u6127','\u614C','\u6168','\u638C','\u63C9','\u63D0',
		'\u63D2','\u63E1','\u63EA','\u63ED','\u63F4','\u6401','\u6402',
		'\u6405','\u641C','\u642D','\u655E','\u6563','\u656C','\u6591',
		'\u65AF','\u666E','\u666F','\u6674','\u6676','\u667A','\u6682',
		'\u6691','\u66FE','\u66FF','\u6700','\u671D','\u671F','\u68C9',
		'\u68CB','\u68CD','\u68D2','\u68D5','\u68DA','\u68EE','\u68F5',
		'\u6905','\u690D','\u6912','\u6B3A','\u6B3E','\u6B96','\u6BEF',
		'\u6E21','\u6E23','\u6E29','\u6E2F','\u6E34','\u6E38','\u6E56',
		'\u6E7E','\u6E7F','\u6E89','\u6ECB','\u6ED1','\u7126','\u7130',
		'\u7136','\u716E','\u724C','\u7334','\u733E','\u7434','\u756A',
		'\u758F','\u75DB','\u767B','\u77ED','\u786C','\u786E','\u79BD',
		'\u7A00','\u7A0B','\u7A0D','\u7A0E','\u7A97','\u7A9C','\u7A9D',
		'\u7AE5','\u7B49','\u7B4B','\u7B50','\u7B51','\u7B52','\u7B54',
		'\u7B56','\u7B5B','\u7B5D','\u7CA5','\u7D2B','\u7D6E',
		'\u7F0E','\u7F13','\u7F16','\u7F18','\u7FA1','\u8054','\u813E',
		'\u814A','\u8154','\u8212','\u8247','\u843D','\u845B','\u8461',
		'\u8463','\u846C','\u8471','\u8475','\u86D9','\u86DB','\u86EE',
		'\u8713','\u8857','\u88C1','\u88C2','\u88C5','\u88D5','\u88D9',
		'\u88E4','\u8C22','\u8C23','\u8C26','\u8D4C','\u8D4F','\u8D54',
		'\u8D81','\u8D85','\u8D8A','\u8D8B','\u8DCC','\u8DD1','\u8DF5',
		'\u8F88','\u8F89','\u8F9C','\u903C','\u9047','\u904D','\u9053',
		'\u9057','\u91CA','\u91CF','\u94F8','\u94FA','\u94FE','\u9500',
		'\u9501','\u9504','\u9505','\u9508','\u950B','\u9510','\u9614',
		'\u9694','\u9699','\u96C1','\u96C4','\u96C5','\u96C6','\u998B',
		'\u9A97','\u9C81','\u9E45','\u9ED1'
	};
	private static final char[] hfStoke13 = {
		'\u50AC','\u50BB','\u50CF','\u52E4','\u53E0','\u55D3','\u584C',
		'\u5851','\u5858','\u585E','\u586B','\u5893','\u5AC1','\u5ACC',
		'\u5E55','\u5EC9','\u5FAE','\u60F3','\u6101','\u6108','\u610F',
		'\u611A','\u611F','\u6148','\u614E','\u640F','\u641E','\u642C',
		'\u643A','\u6444','\u6446','\u6447','\u644A','\u6478','\u6570',
		'\u65B0','\u6696','\u6697','\u695A','\u697C','\u6982','\u6986',
		'\u69D0','\u6B47','\u6BBF','\u6BC1','\u6E90','\u6E9C','\u6EAA',
		'\u6ED4','\u6EDA','\u6EE1','\u6EE4','\u6EE5','\u6EE8','\u6EE9',
		'\u6F20','\u714C','\u714E','\u7164','\u7167','\u732E','\u745E',
		'\u75F0','\u76DF','\u775B','\u7761','\u7763','\u776C','\u77EE',
		'\u788C','\u788D','\u788E','\u7891','\u7897','\u78B0','\u7981',
		'\u798F','\u7A20','\u7B79','\u7B7E','\u7B80','\u7CAE','\u7CB1',
		'\u7F1D','\u7F20','\u7F69','\u7F6A','\u7F6E','\u7FA4','\u8086',
		'\u8165','\u8170','\u8179','\u817E','\u817F','\u8205','\u8499',
		'\u849C','\u84B8','\u84C4','\u84DD','\u84EC','\u86FE','\u8702',
		'\u89E3','\u89E6','\u8A89','\u8C28','\u8D56','\u8DDF','\u8DE8',
		'\u8DEA','\u8DEF','\u8DF3','\u8EB2','\u8F93','\u8F9E','\u8F9F',
		'\u9063','\u9065','\u9119','\u916C','\u9171','\u9274','\u9519',
		'\u9521','\u9523','\u9524','\u9526','\u952E','\u952F','\u969C',
		'\u96F6','\u96F7','\u96F9','\u96FE','\u97F5','\u9B42','\u9E4A',
		'\u9F13','\u9F20','\u9F84'
	};
	private static final char[] hfStoke14 = {
		'\u50DA','\u51F3','\u55FD','\u5609','\u5883','\u5899','\u5AE9',
		'\u5BDF','\u5BE8','\u5F0A','\u613F','\u6155','\u6162','\u622A',
		'\u6454','\u6458','\u6467','\u6487','\u6572','\u65D7','\u66AE',
		'\u699C','\u69A8','\u69B4','\u6A21','\u6B49','\u6B4C','\u6EF4',
		'\u6F02','\u6F06','\u6F0F','\u6F14','\u6F2B','\u7184','\u718A',
		'\u7194','\u7483','\u7591','\u7626','\u78A7','\u78C1','\u7A33',
		'\u7AED','\u7AEF','\u7B97','\u7BA1','\u7BA9','\u7CBE','\u7F29',
		'\u7FE0','\u805A','\u8150','\u8180','\u818A','\u818F','\u819C',
		'\u821E','\u8511','\u853D','\u8718','\u871C','\u8721','\u873B',
		'\u8747','\u88F3','\u88F9','\u8A93','\u8C31','\u8C6A','\u8C8C',
		'\u8D5A','\u8D5B','\u8FA3','\u906D','\u906E','\u9177','\u9178',
		'\u917F','\u9539','\u953B','\u9700','\u9759','\u9897','\u9992',
		'\u9AA1','\u9B44','\u9C9C','\u9F3B'
	};
	private static final char[] hfStoke15 = {
		'\u50F5','\u50FB','\u51DD','\u5288','\u5631','\u5634','\u5668',
		'\u589E','\u58A8','\u58C1','\u5F71','\u5FB7','\u6167','\u6170',
		'\u61C2','\u61D2','\u6469','\u6491','\u6492','\u6495','\u649E',
		'\u64A4','\u64AD','\u64CD','\u6574','\u66B4','\u69FD','\u6A2A',
		'\u6A31','\u6A58','\u6A61','\u6BC5','\u6F5C','\u6F6E','\u6FA1',
		'\u6FC0','\u719F','\u71C3','\u71D5','\u778E','\u7792','\u78E8',
		'\u7A3B','\u7A3C','\u7A3F','\u7BAD','\u7BB1','\u7BC7','\u7BEE',
		'\u7CCA','\u7CD5','\u7CD6','\u7F34','\u806A','\u819B','\u819D',
		'\u81A8','\u8258','\u852C','\u8549','\u8584','\u85AA','\u85AF',
		'\u8774','\u8776','\u878D','\u8861','\u8D5E','\u8D60','\u8D9F',
		'\u8DA3','\u8E0F','\u8E22','\u8E29','\u8E2A','\u8E44','\u8EBA',
		'\u8FA8','\u8FA9','\u9075','\u907F','\u9080','\u9189','\u918B',
		'\u9192','\u9547','\u955C','\u96D5','\u9707','\u9709','\u9760',
		'\u978B','\u9898','\u989C','\u989D','\u98A0','\u98D8','\u9910',
		'\u9ECE','\u9ED8'
	};
	private static final char[] hfStoke16 = {
		'\u6234','\u64E6','\u71E5','\u77A7','\u7A57','\u7CDF','\u7CE0',
		'\u7E41','\u7FFC','\u81C2','\u85CF','\u87BA','\u8D62','\u8E48',
		'\u8FAB','\u971C','\u971E','\u97A0','\u9AA4'
	};
	private static final char[] hfStoke17 = {
		'\u7FFB','\u8986','\u8E66','\u9570','\u97AD','\u9E70'
	};
	private static final char[] hfStoke18 = {
		'\u6500','\u7206','\u74E3','\u7586','\u8B66','\u8E72','\u98A4'
	};
	private static final char[] hfStoke19 = {
		'\u56B7','\u56BC','\u58E4','\u704C','\u7C4D','\u8000','\u8E81',
		'\u9B54'
	};
	private static final char[] hfStoke20 = { '\u8822','\u9732','\u9738' };
	private static final char[] hfStoke21 = { '\u56CA' };
	private static final char[] hfStoke22 = { '\u7F50' };



	private static final char[] shfStoke2 = { '\u5201','\u5315' };
	private static final char[] shfStoke4 = {
		'\u4E10','\u4ED1','\u5197','\u592D','\u6208','\u6B79','\u8BA5',
		'\u9093'
	};
	private static final char[] shfStoke5 = {
		'\u4E4D','\u51AF','\u51F8','\u51F9','\u5362','\u53ED','\u53FD',
		'\u56DA','\u592F','\u5C14','\u7384','\u76BF','\u77E2','\u827E'
	};
	private static final char[] shfStoke6 = {
		'\u4EA5','\u4EF2','\u4F0A','\u4F26','\u51EB','\u5308','\u5401',
		'\u5406','\u540F','\u5415','\u5937','\u5986','\u5C79','\u5EF7',
		'\u5F1B','\u65ED','\u6C5B','\u7EAB','\u808B','\u81FC','\u828B',
		'\u828D','\u8BB3','\u8BB6','\u8BB9','\u8BBC','\u8BC0','\u8FC2',
		'\u8FC4','\u90A2','\u90A6','\u9631','\u9A6E','\u9A6F'
	};
	private static final char[] shfStoke7 = {
		'\u4F3A','\u4F43','\u4F51','\u5151','\u5228','\u5323','\u5364',
		'\u541D','\u541F','\u5420','\u542D','\u542E','\u5431','\u543B',
		'\u5450','\u5455','\u545B','\u56E4','\u56F1','\u574E','\u575E',
		'\u5760','\u5992','\u5993','\u59CA','\u5C41','\u5C96','\u5DEB',
		'\u5E87','\u5E90','\u5F64','\u5FF1','\u6273','\u627C','\u6291',
		'\u6292','\u62A0','\u62A1','\u62DF','\u6748','\u6749','\u6756',
		'\u6C5E','\u6C70','\u6C79','\u6C90','\u6C9B','\u6CA5','\u6CA6',
		'\u6CA7','\u6CAA','\u7078','\u707C','\u7261','\u72C8','\u7396',
		'\u739B','\u752B','\u7538','\u7EAC','\u7F55','\u8096','\u8098',
		'\u809B','\u8299','\u829C','\u82A5','\u82AD','\u82AF','\u82C7',
		'\u8BC5','\u8BC8','\u8F69','\u9091','\u95F0','\u97E7','\u9E20'
	};
	private static final char[] shfStoke8 = {
		'\u4F88','\u4FA0','\u4FA3','\u4FA5','\u51FD','\u5239','\u523D',
		'\u5351','\u5352','\u5353','\u5366','\u53C1','\u5475','\u547B',
		'\u5486','\u5492','\u5495','\u5496','\u5499','\u54CE','\u5764',
		'\u576A','\u576F','\u5777','\u5944','\u5948','\u59C6','\u5B9B',
		'\u5BA0','\u5C49','\u5CB3','\u5E15','\u5E1A','\u5E9E','\u5F25',
		'\u5F27','\u5FFF','\u6014','\u602F','\u62C2','\u62C4','\u62C7',
		'\u62D3','\u62D7','\u62D9','\u62E7','\u6614','\u6619','\u676D',
		'\u6789','\u679A','\u67A2','\u67AB','\u6B67','\u6BB4','\u6C13',
		'\u6C1B','\u6CAE','\u6CBC','\u6CBD','\u6CCC','\u6CDE','\u6CE3',
		'\u70AC','\u72DE','\u73AB','\u74EE','\u7599','\u759A','\u759F',
		'\u77FE','\u7948','\u79C9','\u7EC5','\u7ECA','\u7ECE','\u80AA',
		'\u80AE','\u80B4','\u82D4','\u82DB','\u82DE','\u82DF','\u82EB',
		'\u8301','\u8309','\u8671','\u8869','\u89C5','\u8BE1','\u8D26',
		'\u8D2C','\u8D2E','\u90C1','\u964B','\u964C','\u9A79'
	};
	private static final char[] shfStoke9 = {
		'\u4FAF','\u4FC4','\u4FCF','\u4FD0','\u52C3','\u52CB','\u54A7',
		'\u54A8','\u54AA','\u54C6','\u54DF','\u579B','\u57A2','\u5951',
		'\u5955','\u59DA','\u5A04','\u5A1C','\u5BA6','\u5C4F',
		'\u5CE6','\u5E7D','\u5F8A','\u6043','\u604D','\u6064','\u606C',
		'\u62ED','\u62EF','\u62F1','\u62F7','\u631F','\u6627','\u662D',
		'\u6635','\u67B7','\u67D1','\u67D2','\u67E0','\u67EC','\u6805',
		'\u6808','\u6BE1','\u6C22','\u6CF5','\u6D1B','\u6D3C','\u6D8E',
		'\u70AB','\u70C1','\u72F0','\u73B2','\u73B7','\u73CA','\u76C5',
		'\u76F9','\u7802','\u781A','\u7960','\u79D5','\u7C7D','\u80CE',
		'\u80DA','\u80E7','\u832C','\u8334','\u8335','\u8338','\u8346',
		'\u8354','\u835A','\u835E','\u8360','\u8364','\u8367','\u8650',
		'\u86A4','\u884D','\u8BEB','\u8BEC','\u8BF2','\u8D30','\u8F74',
		'\u900A','\u9499','\u949D','\u94A0','\u94A6','\u94A7','\u94AE',
		'\u95FA','\u95FD','\u9668','\u97ED','\u98D2','\u9975','\u9A87',
		'\u9E25'
	};
	private static final char[] shfStoke10 = {
		'\u4FFA','\u5014','\u51C4','\u51CC','\u5254','\u533F','\u537F',
		'\u54E9','\u54EE','\u54FA','\u54FC','\u5501','\u5506','\u5520',
		'\u5527','\u5703','\u57C2','\u57C3','\u5A29','\u5CED','\u5CFB',
		'\u6055','\u608D','\u60AF','\u631A','\u632B','\u6342','\u6345',
		'\u634C','\u634D','\u6363','\u658B','\u6813','\u6816','\u6845',
		'\u6866','\u6869','\u6886','\u6B89','\u6BB7','\u6C28','\u6D66',
		'\u6D95','\u6DA1','\u6DA3','\u6DA4','\u6DA7','\u6DA9','\u70D9',
		'\u74F7','\u7554','\u75B9','\u7830','\u7838','\u783E','\u795F',
		'\u79E6','\u79EB','\u7A8D','\u7B06','\u7D0A','\u7F94','\u8018',
		'\u8019','\u8038','\u803F','\u8042','\u80EF','\u80F0','\u8110',
		'\u8113','\u8200','\u8378','\u8389','\u83B1','\u83B9','\u83BA',
		'\u83BD','\u868C','\u8693','\u869C','\u86A3','\u86AA','\u8877',
		'\u8881','\u8892','\u8BFA','\u8BFD','\u8C06','\u8C79','\u8C7A',
		'\u8D3E','\u8D41','\u8D42','\u8D43','\u901B','\u901E','\u90ED',
		'\u914C','\u94BE','\u94C6','\u9881','\u9981','\u9A8F','\u9E2F',
		'\u9E33','\u9E35'
	};
	private static final char[] shfStoke11 = {
		'\u4E7E','\u504E','\u5080','\u515C','\u5195','\u51F0','\u52D8',
		'\u533E','\u53A2','\u552C','\u553E','\u5543','\u5561','\u5564',
		'\u5565','\u5570','\u5578','\u57E0','\u5815','\u5962','\u5A36',
		'\u5A49','\u5A74','\u5BC2','\u5C09','\u5D0E','\u5D14','\u5D29',
		'\u5DE2','\u5EB5','\u5EB6','\u5F6A','\u5F6C','\u5F98','\u5F99',
		'\u60B4','\u60CB','\u60E6','\u6376','\u637A','\u637B','\u6382',
		'\u6390','\u6396','\u63AA','\u63B7','\u63B8','\u63BA','\u655B',
		'\u6664','\u6666','\u66F9','\u66FC','\u6897','\u68A7','\u68AD',
		'\u6DAE','\u6DAF','\u6DB5','\u6DC0','\u6DC6','\u6DCC','\u6DD1',
		'\u6DE4','\u6DEB','\u6DEE','\u6DF3','\u6E0A','\u70F9','\u710A',
		'\u7115','\u7316','\u7405','\u7409','\u7410','\u7566','\u75CA',
		'\u76D4','\u7736','\u7737','\u77EB','\u7845','\u7855','\u796D',
		'\u7977','\u79F8','\u79FD','\u7A92','\u7B19','\u7B24','\u7EF0',
		'\u7EF7','\u7EFC','\u7EFD','\u7F00','\u7FCE','\u804A','\u812F',
		'\u8235','\u8236','\u8237','\u83C7','\u83E9','\u83F1','\u83F2',
		'\u840E','\u8424','\u8427','\u8428','\u86AF','\u86C0','\u86C6',
		'\u86C9','\u8845','\u88B1','\u88C6','\u8C0D','\u8C10','\u8C12',
		'\u8C13','\u8C1A','\u8D4A','\u8D66','\u8DBE','\u8EAF','\u9038',
		'\u903B','\u9157','\u915D','\u94D0','\u94DB','\u94DD','\u94E1',
		'\u94E3','\u94ED','\u960E','\u9610','\u9685','\u9885','\u9887',
		'\u9E3F','\u9EB8'
	};
	private static final char[] shfStoke12 = {
		'\u51FF','\u52DF','\u557C','\u55A7','\u55B3','\u55BB','\u5830',
		'\u58F9','\u5960','\u5A7F','\u5A92','\u5A9A','\u5BD3','\u5D4C',
		'\u5F6D','\u60EB','\u60F6','\u6115','\u63B0','\u63CD','\u63D6',
		'\u63E3','\u63E9','\u63FD','\u6400','\u6413','\u6414','\u6566',
		'\u6670','\u667E','\u68D8','\u68E0','\u68F1','\u68FA','\u690E',
		'\u692D','\u6930','\u6994','\u6C2E','\u6C2F','\u6E24','\u6E3A',
		'\u6E43','\u6E58','\u6E83','\u6E85','\u6EDE','\u7119','\u711A',
		'\u724D','\u7280','\u7329','\u732C','\u7422','\u7433','\u743C',
		'\u7525','\u7574','\u75D8','\u75E2','\u75EA','\u785D','\u786B',
		'\u7A96','\u7A98','\u7AE3','\u7B4F','\u7C9F','\u7CA4','\u7F05',
		'\u7F06','\u7F14','\u7F15','\u7FD4','\u7FD8','\u814B','\u814C',
		'\u8155','\u846B','\u8482','\u848B','\u86D4','\u86E4','\u8712',
		'\u8C24','\u8D4B','\u8D4E','\u8D50','\u8DCB','\u8DDB','\u903E',
		'\u9042','\u904F','\u9163','\u9165','\u9509','\u950C','\u9698',
		'\u96C7','\u96F3','\u97E9','\u988A','\u9A9A','\u9E43','\u9ECD',
		'\u9F0E'
	};
	private static final char[] shfStoke13 = {
		'\u527F','\u55C5','\u55DC','\u55E1','\u55E4','\u55E6','\u5AB3',
		'\u5AC9','\u5BDD','\u5BDE','\u5E4C','\u5ED3','\u642A','\u659F',
		'\u6687','\u693F','\u6954','\u695E','\u6963','\u6977','\u6984',
		'\u6EA2','\u6EAF','\u6EB6','\u6EBA','\u6ED3','\u6F13','\u715E',
		'\u733F','\u745F','\u7470','\u7578','\u75F4','\u75F9','\u7766',
		'\u7779','\u7784','\u787C','\u7889','\u7898','\u7980','\u7A1A',
		'\u7A9F','\u7AA5','\u7B77','\u7F1A','\u7F24','\u7F72','\u8058',
		'\u8084','\u816E','\u817A','\u817B','\u84B2','\u84BF','\u84C9',
		'\u84D6','\u86F9','\u8700','\u8708','\u8715','\u8717','\u8859',
		'\u88F8','\u8902','\u8A8A','\u8C2C','\u8DF7','\u8DFA','\u8F90',
		'\u8F91','\u916A','\u951A','\u9525','\u9528','\u952D','\u9530',
		'\u96CF','\u9756','\u9774','\u9776','\u9891','\u9893','\u9896',
		'\u998D','\u998F','\u9B41','\u9E49','\u9E4F'
	};
	private static final char[] shfStoke14 = {
		'\u50E7','\u5162','\u5600','\u5601','\u5885','\u5AE1','\u5B75',
		'\u5BE1','\u5BE5','\u5E54','\u5F70','\u6177','\u6479','\u6995',
		'\u699B','\u6F29','\u6F31','\u6F3E','\u718F','\u7199','\u71AC',
		'\u761F','\u7629','\u789F','\u78B1','\u78B3','\u78B4','\u7B8D',
		'\u7B95','\u7BAB','\u7CB9','\u7F28','\u8206','\u8214','\u8513',
		'\u8517','\u851A','\u852B','\u853C','\u8749','\u8910','\u892A',
		'\u8C2D','\u8D58','\u8D6B','\u8E0A','\u8F95','\u8F96','\u9175',
		'\u9540','\u96A7','\u96CC'
	};
	private static final char[] shfStoke15 = {
		'\u51DB','\u5632','\u5636','\u5639','\u563F','\u58A9','\u5B09',
		'\u5C65','\u5E62','\u618B','\u618E','\u6194','\u61A8','\u61CA',
		'\u64A9','\u64AC','\u64AE','\u64B0','\u64B5','\u64D2','\u6577',
		'\u6A0A','\u6A1F','\u6A44','\u6F58','\u6F66','\u6F6D','\u6F84',
		'\u6F88','\u6F8E','\u6F9C','\u6FB3','\u7624','\u762A','\u762B',
		'\u78BE','\u78C5','\u78D5','\u7A3D','\u7BD3','\u7F2D','\u7FE9',
		'\u8198','\u854A','\u8574','\u874C','\u874E','\u8757','\u8759',
		'\u8760','\u8912','\u8925','\u8C34','\u8C4C','\u8C6B','\u9187',
		'\u954A','\u9550','\u978D','\u9CA4','\u9CAB','\u9E64'
	};
	private static final char[] shfStoke16 = {
		'\u5112','\u5180','\u5669','\u566A','\u61BE','\u61C8','\u64BC',
		'\u64C2','\u64C5','\u64CE','\u6A59','\u6A71','\u6FD2','\u71CE',
		'\u74E2','\u7638','\u763E','\u7A46','\u7ABF','\u7BD9','\u7BE1',
		'\u7BF1','\u7BF7','\u7CD9','\u7F30','\u7FF0','\u81B3','\u857E',
		'\u8587','\u859B','\u8783','\u879F','\u87C6','\u87E5','\u8E31',
		'\u8E42','\u8F99','\u970D','\u970E','\u9CB8','\u9E66','\u9ED4'
	};
	private static final char[] shfStoke17 = {
		'\u5121','\u568E','\u56A3','\u58D5','\u5FBD','\u61E6','\u6233',
		'\u66D9','\u6726','\u6A80','\u6A90','\u6AA9','\u6AAC','\u7011',
		'\u7235','\u74A7','\u764C','\u765E','\u77AA','\u77AC','\u77AD',
		'\u77B3','\u77BB','\u78F7','\u7901','\u7C07','\u7CDC','\u81C0',
		'\u81CA','\u85D0','\u85D5','\u85E4','\u87C0','\u87CB','\u895F',
		'\u8C41','\u8D61','\u8E4B','\u9563','\u9B4F','\u9CC4','\u9CCD'
	};
	private static final char[] shfStoke19 = {
		'\u5B7D','\u6512','\u7663','\u7C38','\u7C3F','\u7FB9','\u85FB',
		'\u8611','\u87F9','\u8E6C','\u8E6D','\u9761','\u9CD6'
	};
	private static final char[] shfStoke20 = { '\u5DCD','\u6518','\u7CEF','\u8815','\u8B6C','\u9B13','\u9CDE' };
	private static final char[] shfStoke21 = { '\u8E8F','\u9739','\u9AD3' };
	private static final char[] shfStoke22 = { '\u74E4','\u8638','\u9576' };
	private static final char[] shfStoke24 = { '\u77D7' };

	public static final CharTable HighFreqStoke1 = new CharTable(hfStoke1);
	public static final CharTable HighFreqStoke2 = new CharTable(hfStoke2);
	public static final CharTable HighFreqStoke3 = new CharTable(hfStoke3);
	public static final CharTable HighFreqStoke4 = new CharTable(hfStoke4);
	public static final CharTable HighFreqStoke5 = new CharTable(hfStoke5);
	public static final CharTable HighFreqStoke6 = new CharTable(hfStoke6);
	public static final CharTable HighFreqStoke7 = new CharTable(hfStoke7);
	public static final CharTable HighFreqStoke8 = new CharTable(hfStoke8);
	public static final CharTable HighFreqStoke9 = new CharTable(hfStoke9);
	public static final CharTable HighFreqStoke10 = new CharTable(hfStoke10);
	public static final CharTable HighFreqStoke11 = new CharTable(hfStoke11);
	public static final CharTable HighFreqStoke12 = new CharTable(hfStoke12);
	public static final CharTable HighFreqStoke13 = new CharTable(hfStoke13);
	public static final CharTable HighFreqStoke14 = new CharTable(hfStoke14);
	public static final CharTable HighFreqStoke15 = new CharTable(hfStoke15);
	public static final CharTable HighFreqStoke16 = new CharTable(hfStoke16);
	public static final CharTable HighFreqStoke17 = new CharTable(hfStoke17);
	public static final CharTable HighFreqStoke18 = new CharTable(hfStoke18);
	public static final CharTable HighFreqStoke19 = new CharTable(hfStoke19);
	public static final CharTable HighFreqStoke20 = new CharTable(hfStoke20);
	public static final CharTable HighFreqStoke21 = new CharTable(hfStoke21);
	public static final CharTable HighFreqStoke22 = new CharTable(hfStoke22);


	public static final CharTable SecondHighFreqStoke2 = new CharTable(shfStoke2);
	public static final CharTable SecondHighFreqStoke4 = new CharTable(shfStoke4);
	public static final CharTable SecondHighFreqStoke5 = new CharTable(shfStoke5);
	public static final CharTable SecondHighFreqStoke6 = new CharTable(shfStoke6);
	public static final CharTable SecondHighFreqStoke7 = new CharTable(shfStoke7);
	public static final CharTable SecondHighFreqStoke8 = new CharTable(shfStoke8);
	public static final CharTable SecondHighFreqStoke9 = new CharTable(shfStoke9);
	public static final CharTable SecondHighFreqStoke10 = new CharTable(shfStoke10);
	public static final CharTable SecondHighFreqStoke11 = new CharTable(shfStoke11);
	public static final CharTable SecondHighFreqStoke12 = new CharTable(shfStoke12);
	public static final CharTable SecondHighFreqStoke13 = new CharTable(shfStoke13);
	public static final CharTable SecondHighFreqStoke14 = new CharTable(shfStoke14);
	public static final CharTable SecondHighFreqStoke15 = new CharTable(shfStoke15);
	public static final CharTable SecondHighFreqStoke16 = new CharTable(shfStoke16);
	public static final CharTable SecondHighFreqStoke17 = new CharTable(shfStoke17);
	public static final CharTable SecondHighFreqStoke19 = new CharTable(shfStoke19);
	public static final CharTable SecondHighFreqStoke20 = new CharTable(shfStoke20);
	public static final CharTable SecondHighFreqStoke21 = new CharTable(shfStoke21);
	public static final CharTable SecondHighFreqStoke22 = new CharTable(shfStoke22);
	public static final CharTable SecondHighFreqStoke24 = new CharTable(shfStoke24);

	public static final CharTable HighFreqStokeLE10;
	public static final CharTable HighFreqStokeM10;
	public static final CharTable HighFreqAll;

	public static final CharTable SecondHighFreqStokeLE10;
	public static final CharTable SecondHighFreqStokeM10;
	public static final CharTable SecondHighFreqAll;

	public static final CharTable HfShfStokeLE10;
	public static final CharTable HfShfStokeM10;
	public static final CharTable HfShfStokeAll;


	static {

		HighFreqStokeLE10 = new CharTable(
			new char[][] {
				hfStoke1,
				hfStoke2,
				hfStoke3,
				hfStoke4,
				hfStoke5,
				hfStoke6,
				hfStoke7,
				hfStoke8,
				hfStoke9,
				hfStoke10
			});
		HighFreqStokeM10 = new CharTable(
			new char[][] {
				hfStoke11,
				hfStoke12,
				hfStoke13,
				hfStoke14,
				hfStoke15,
				hfStoke16,
				hfStoke17,
				hfStoke18,
				hfStoke19,
				hfStoke20,
				hfStoke21,
				hfStoke22
			});
		HighFreqAll = new CharTable(
			new char[][] {
				hfStoke1,
				hfStoke2,
				hfStoke3,
				hfStoke4,
				hfStoke5,
				hfStoke6,
				hfStoke7,
				hfStoke8,
				hfStoke9,
				hfStoke10,
				hfStoke11,
				hfStoke12,
				hfStoke13,
				hfStoke14,
				hfStoke15,
				hfStoke16,
				hfStoke17,
				hfStoke18,
				hfStoke19,
				hfStoke20,
				hfStoke21,
				hfStoke22
			});

		SecondHighFreqStokeLE10 = new CharTable(
			new char[][] {
				shfStoke2,
				shfStoke4,
				shfStoke5,
				shfStoke6,
				shfStoke7,
				shfStoke8,
				shfStoke9,
				shfStoke10
			});
		SecondHighFreqStokeM10 = new CharTable(
			new char[][] {
				shfStoke11,
				shfStoke12,
				shfStoke13,
				shfStoke14,
				shfStoke15,
				shfStoke16,
				shfStoke17,
				shfStoke19,
				shfStoke20,
				shfStoke21,
				shfStoke22,
				shfStoke24
			});
		SecondHighFreqAll = new CharTable(
			new char[][] {
				shfStoke2,
				shfStoke4,
				shfStoke5,
				shfStoke6,
				shfStoke7,
				shfStoke8,
				shfStoke9,
				shfStoke10,
				shfStoke11,
				shfStoke12,
				shfStoke13,
				shfStoke14,
				shfStoke15,
				shfStoke16,
				shfStoke17,
				shfStoke19,
				shfStoke20,
				shfStoke21,
				shfStoke22,
				shfStoke24
			});

		HfShfStokeLE10 = new CharTable(
			new char[][] {
				HighFreqStokeLE10.getTable(),
				SecondHighFreqStokeLE10.getTable(),
			});
		HfShfStokeM10 = new CharTable(
			new char[][] {
				HighFreqStokeM10.getTable(),
				SecondHighFreqStokeM10.getTable(),
			});
		HfShfStokeAll = new CharTable(
			new char[][] {
				HighFreqAll.getTable(),
				SecondHighFreqAll.getTable(),
			});
	}





}
